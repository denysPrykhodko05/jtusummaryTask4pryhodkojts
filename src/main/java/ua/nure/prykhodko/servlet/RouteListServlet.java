package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.exception.Messages;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route")
public class RouteListServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RouteListServlet.class);

    @Override
    public void init() throws ServletException {
        log.info(Messages.INFO_ENTER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer train_id = Integer.parseInt(req.getParameter("id"));
        ServletContext servletContext = req.getServletContext();
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        int route_id = routeDAO.getRouteIdByTrainId(train_id);
        log.trace(Messages.TRACE_ROUTE_FOUND_ID+route_id);
        if (route_id != 0) {
            req.setAttribute("route", stationDAO.getStationByRouteId(route_id));
            req.getRequestDispatcher("jsp/RoutePage.jsp").forward(req, resp);
        }else{
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
        log.info(Messages.INFO_EXIT);
    }
}
