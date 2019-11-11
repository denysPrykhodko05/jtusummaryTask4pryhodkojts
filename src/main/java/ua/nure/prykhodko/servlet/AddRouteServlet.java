package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.entity.Train;
import ua.nure.prykhodko.exception.Messages;
import ua.nure.prykhodko.utils.TimeUtils;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/admin/routeAdd")
public class AddRouteServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddRouteServlet.class);

    @Override
    public void init() throws ServletException {
        log.info("Enter to servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        List<Train> availableTrain = routeDAO.getAvailableTrain();
        log.trace(Messages.TRACE_ROUTE_FOUND_ALL_AVAILABLE_TRAINS+ availableTrain);
        req.setAttribute("trainList", availableTrain);
        req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");

        Route route = new Route();
        int route_id = 0;
        boolean timeFlag=false;

        Station startStation = null;
        int start_id = 0;
        final String startStationName = req.getParameter("startName");
        final String departDate = req.getParameter("departDate");
        final String departTime = req.getParameter("departTime");
        Timestamp departTimestamp = null;

        int final_id = 0;
        Station finalStation = null;
        final String finalStationName = req.getParameter("finalName");
        final String arriveDate = req.getParameter("arriveDate");
        final String arriveTime = req.getParameter("arriveTime");
        Timestamp arriveTimestamp = null;

        final String train = req.getParameter("id");

        if (startStationName != null && Validation.isCorrectStationName(startStationName)) {
            start_id = stationDAO.getEntityID(startStationName);
            log.trace(Messages.TRACE_STATION_FOUND+start_id);
        } else {
            req.setAttribute("startName", startStationName);
            req.setAttribute("finalName", finalStationName);
            req.setAttribute("errorStart", true);
            req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
        }

        if (finalStationName != null && Validation.isCorrectStationName(finalStationName)) {
            final_id = stationDAO.getEntityID(finalStationName);
            log.trace(Messages.TRACE_STATION_FOUND+final_id);
        } else {
            req.setAttribute("errorFinal", true);
            req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
        }

        arriveTimestamp = TimeUtils.parseStringToTimestamp(arriveDate, arriveTime);
        departTimestamp = TimeUtils.parseStringToTimestamp(departDate, departTime);

        if (arriveTimestamp == null) {
            timeFlag=true;
            req.setAttribute("errorArrive", true);
            req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
        }
        if (departTimestamp == null) {
            timeFlag=true;
            req.setAttribute("errorDepart", true);
            req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
        }

        if (train != null && !train.equals("")) {
            route.setTrainId(Integer.parseInt(train));
        } else {
            req.setAttribute("errorTrain", true);
            req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
        }


        if (start_id != 0) {
            route.setStartPoint_id(start_id);
            route.setDepart_time(departTimestamp);
        } else {
            stationDAO.addEntity(new Station(startStationName));
            start_id = stationDAO.getEntityID(startStationName);
            log.trace(Messages.TRACE_STATION_FOUND+start_id);
            route.setStartPoint_id(start_id);
            route.setDepart_time(departTimestamp);
        }

        if (final_id != 0) {
            route.setFinalPoint_id(final_id);
            route.setArrive_time(arriveTimestamp);
        } else {
            stationDAO.addEntity(new Station(finalStationName));
            final_id = stationDAO.getEntityID(finalStationName);
            route.setFinalPoint_id(final_id);
            route.setArrive_time(arriveTimestamp);
        }
        if (!timeFlag && arriveTimestamp.getTime() - departTimestamp.getTime() > 0) {
            routeDAO.addEntity(route);
            route_id = routeDAO.getRouteIdByTrainId(Integer.parseInt(train));
            log.trace(Messages.TRACE_ROUTE_FOUND_ID+route_id);
            startStation = new Station();
            startStation.setRoute_id(route_id);
            startStation.setId(start_id);
            startStation.setDepart_time(departTimestamp);
            routeDAO.addStartStationInStationRoute(startStation);
            log.trace(Messages.TRACE_STATION_CREATE_ON_ROUTE + startStationName);
            finalStation = new Station();
            finalStation.setRoute_id(route_id);
            finalStation.setId(final_id);
            finalStation.setArrive_time(arriveTimestamp);
            routeDAO.addFinalStationInRoute(finalStation);
            log.trace(Messages.TRACE_STATION_CREATE_ON_ROUTE + finalStationName);


            resp.sendRedirect("/admin");
        }else{
            req.setAttribute("errorTime", true);
            req.getRequestDispatcher("/jsp/AddRoutePage.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        log.info("Exit from servlet");
    }
}
