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
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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
        List<Integer> routesId=null;
        final String from = req.getParameter("from");
        final String to = req.getParameter("to");
        FinalRoute finalRoute;
        Train train;
        StationDAO stationDAO =(StationDAO) servletContext.getAttribute("stationDAO");
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        TrainDAO trainDAO = (TrainDAO) servletContext.getAttribute("trainDAO");

        Station stationFrom = stationDAO.getStationByName(from);
        Station stationTo = stationDAO.getStationByName(to);


        if(stationFrom!=null && stationTo!=null) {
           routesId = routeDAO.getRouteByStationId(stationFrom.getId(), stationTo.getId());
           for (int id: routesId) {

               stationDAO.getTimeForStation(stationFrom,id);
               stationDAO.getTimeForStation(stationTo,id);

               Route route = routeDAO.getEntityById(id);
               train = trainDAO.getEntityById(route.getTrainId());

               finalRoute = new FinalRoute();

               finalRoute.setTrainId(route.getTrainId());
               finalRoute.setStartStationName(from);
               finalRoute.setFinalStationName(to);
               finalRoute.setDepart_time(stationFrom.getDepart_time());
               finalRoute.setArrive_time(stationTo.getArrive_time());
               finalRoute.setCompartment(train.getCompartment());
               finalRoute.setEconomy_class(train.getEconomy_class());
               finalRoute.setCommon(train.getCommon());
               finalRouteList.add(finalRoute);
           }
        }
        req.setAttribute("search", true);
        req.setAttribute("routes", finalRouteList);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {

    }
}
