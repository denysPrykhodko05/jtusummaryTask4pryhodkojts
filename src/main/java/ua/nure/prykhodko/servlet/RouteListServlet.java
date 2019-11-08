package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route")
public class RouteListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer train_id = Integer.parseInt(req.getParameter("id"));
        ServletContext servletContext = req.getServletContext();
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        int route_id = routeDAO.getRouteIdByTrainId(train_id);
        if (route_id != 0) {
            req.setAttribute("route", stationDAO.getStationByRouteId(route_id));
            req.getRequestDispatcher("jsp/RoutePage.jsp").forward(req, resp);
        }else{
            resp.sendRedirect("/");
        }
    }
}
