package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.exception.Messages;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/routeDelete")
public class DeleteRouteServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteRouteServlet.class);

    @Override
    public void init() throws ServletException {
        log.info(Messages.INFO_ENTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");

        final String route_id = req.getParameter("route");

        if (routeDAO.isExistRoute(Integer.parseInt(route_id))) {
            routeDAO.deleteRouteFromStationRoute(Integer.parseInt(route_id));
            log.trace(Messages.TRACE_DELETE_ROUTE_FROM_STATION_ROUTE+route_id);
            routeDAO.delete(Integer.parseInt(route_id));
            log.trace(Messages.TRACE_DELETE_ROUT+route_id);
            resp.sendRedirect("/admin");
        } else {
            req.setAttribute("route", route_id);
            req.setAttribute("errorInput", true);
            req.getRequestDispatcher("/jsp/DeleteRoutePage.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        log.info(Messages.INFO_EXIT);
    }

}
