package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.dao.SqlDAO.TrainDAO;
import ua.nure.prykhodko.entity.FinalRoute;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.entity.Train;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.*;

@WebServlet("/search")
public class SearchRouteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            StringBuilder sb = new StringBuilder();
            sb.append(date).append(" ").append(time).append(":00").append(".0");
            timeMin = Timestamp.valueOf(sb.toString());
            for (int id : routesId) {
                stationDAO.getTimeForStation(stationFrom, id);
                stationDAO.getTimeForStation(stationTo, id);
                if (stationFrom.getDepart_time() != null &&
                        stationTo.getArrive_time() != null &&
                        compareDayOfWeek(Timestamp.valueOf(sb.toString()),stationFrom.getDepart_time())&&
                        !compareTime(timeMin,stationFrom.getDepart_time())&&
                        compareTime(stationTo.getArrive_time(),stationFrom.getDepart_time())) {
                    sb = new StringBuilder();

                    Route route = routeDAO.getEntityById(id);
                    train = trainDAO.getEntityById(route.getTrainId());

                    timeInRoad = countTimeInRoad(stationTo, stationFrom);
                    timeFrom = sb.append(date).append(" ").append(parseDate(stationFrom.getDepart_time())).toString();
                    date = req.getParameter("date");
                    sb = new StringBuilder();
                    timeTo = sb.append(date).append(" ").append(parseDate(stationTo.getArrive_time())).toString();

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
                    finalRoute.setEconomy_class(train.getEconomy_class());
                    finalRoute.setCommon(train.getCommon());
                    finalRouteList.add(finalRoute);
                    req.setAttribute("foundRoute", true);
                    req.setAttribute("search", true);
                    req.setAttribute("routes", finalRouteList);
                }
                stationFrom = stationDAO.getStationByName(from);
                stationTo = stationDAO.getStationByName(to);
            }
        }
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private String parseDate(Timestamp timestamp) {
        String dateTime = timestamp.toString();
        String[] parsedString = dateTime.split("\\s");
        String time = parsedString[1];
        return time;
    }

    private String countTimeInRoad(Station stationTo, Station stationFrom) {
        StringBuilder sb = new StringBuilder();
        Long timeInRoad = stationTo.getArrive_time().getTime() - stationFrom.getDepart_time().getTime();

        timeInRoad /= 60000;
        long hours = 0;
        long minutes = 0;
        hours = timeInRoad / 60;
        minutes = timeInRoad % 60;

        sb.append(hours).append(":").append(minutes);

        return sb.toString();
    }

    private boolean compareDayOfWeek(Timestamp first, Timestamp second){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        Date d = null;
        Date d2 =null;
        try {
            d = sdf.parse(first.toString());
            d2 = sdf.parse(second.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(d);
        cal2.setTime(d2);
        return cal.get(Calendar.DAY_OF_WEEK) == cal2.get(Calendar.DAY_OF_WEEK);
    }

    private boolean compareTime(Timestamp to, Timestamp from) {
        Long time = to.getTime() - from.getTime();
        return time > 0;
    }

    @Override
    public void init() throws ServletException {

    }
}
