package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.dao.SqlDAO.TrainDAO;
import ua.nure.prykhodko.bean.FinalRoute;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.entity.Train;
import ua.nure.prykhodko.exception.Messages;
import ua.nure.prykhodko.utils.TimeUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/search")
public class SearchRouteServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(SearchRouteServlet.class);


    @Override
    public void init() throws ServletException {
        log.trace(Messages.INFO_ENTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();

        List<FinalRoute> finalRouteList = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        List<Integer> routesId = null;
        final String from = req.getParameter("from");
        final String to = req.getParameter("to");
        String date = req.getParameter("date");
        final String time = req.getParameter("time");
        String timeFrom;
        String timeTo;
        String timeInRoad;
        Timestamp timeMin;
        double priceForCommon;
        double priceForCompartment;
        double priceForEconomy;

        FinalRoute finalRoute;
        Train train;
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        TrainDAO trainDAO = (TrainDAO) servletContext.getAttribute("trainDAO");

        Station stationFrom = stationDAO.getStationByName(from);
        Station stationTo = stationDAO.getStationByName(to);

        req.setAttribute("foundRoute", false);

        if (stationFrom != null && stationTo != null) {
            routesId = routeDAO.getRouteByStationId(stationFrom.getId(), stationTo.getId());
            log.trace(Messages.TRACE_STATION_FOUND + routesId);
            StringBuilder sb = new StringBuilder();
            sb.append(date).append(" ").append(time).append(":00").append(".0");
            timeMin = Timestamp.valueOf(sb.toString());
            for (int id : routesId) {
                stationDAO.getTimeForStation(stationFrom, id);
                log.trace(Messages.TRACE_STATION_TIME+stationFrom);
                stationDAO.getTimeForStation(stationTo, id);
                log.trace(Messages.TRACE_STATION_TIME+stationTo);
                if (stationFrom.getDepart_time() != null &&
                        stationTo.getArrive_time() != null &&
                        TimeUtils.compareDayOfWeek(Timestamp.valueOf(sb.toString()), stationFrom.getDepart_time()) &&
                        TimeUtils.timeFilter(timeMin, stationFrom.getDepart_time()) &&
                        TimeUtils.compareTimeSation(stationTo.getArrive_time(), stationFrom.getDepart_time())) {
                    sb = new StringBuilder();

                    Route route = routeDAO.getEntityById(id);
                    train = trainDAO.getEntityById(route.getTrainId());
                    log.trace(Messages.TRACE_FOUND_TRAIN+train);

                    timeInRoad = TimeUtils.countTimeInRoad(stationTo, stationFrom);
                    timeFrom = sb.append(date).append(" ").append(TimeUtils.parseDate(stationFrom.getDepart_time())).toString();
                    date = req.getParameter("date");
                    sb = new StringBuilder();
                    timeTo = sb.append(date).append(" ").append(TimeUtils.parseDate(stationTo.getArrive_time())).toString();


                    priceForCommon = TimeUtils.parseTime(timeInRoad) * 30;
                    priceForCompartment = TimeUtils.parseTime(timeInRoad) * 60;
                    priceForEconomy = TimeUtils.parseTime(timeInRoad) * 45;

                    finalRoute = new FinalRoute();

                    finalRoute.setTrainId(route.getTrainId());
                    finalRoute.setStartStationName(from);
                    finalRoute.setFinalStationName(to);
                    timeFrom = timeFrom.replace(".0", "");
                    timeTo = timeTo.replace(".0", "");
                    finalRoute.setDepart_time(timeFrom);
                    finalRoute.setArrive_time(timeTo);
                    finalRoute.setTimeInRoad(timeInRoad);
                    finalRoute.setCompartment(train.getCompartment());
                    finalRoute.setEconomyClass(train.getEconomyClass());
                    finalRoute.setCommon(train.getCommon());
                    finalRoute.setPriceForCommon(priceForCommon);
                    finalRoute.setPriceForCompartment(priceForCompartment);
                    finalRoute.setPriceForEconomy(priceForEconomy);
                    finalRouteList.add(finalRoute);
                    req.setAttribute("foundRoute", true);
                    req.setAttribute("search", true);
                    req.setAttribute("routes", finalRouteList);
                }
                stationFrom = stationDAO.getStationByName(from);
                log.trace(Messages.TRACE_STATION_FOUND+stationFrom);
                stationTo = stationDAO.getStationByName(to);
                log.trace(Messages.TRACE_STATION_FOUND+to);

            }
        }
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        log.info(Messages.INFO_EXIT);
    }
}
