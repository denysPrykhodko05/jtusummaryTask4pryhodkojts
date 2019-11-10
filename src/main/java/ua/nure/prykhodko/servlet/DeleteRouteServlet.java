package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.entity.Route;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/routeDelete")
public class DeleteRouteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");

        final String route_id = req.getParameter("route");

        if (routeDAO.isExistRoute(Integer.parseInt(route_id))){
            routeDAO.deleteRouteFromStationRoute(Integer.parseInt(route_id));
            routeDAO.delete(Integer.parseInt(route_id));
            resp.sendRedirect("/admin");
        }else{
            req.setAttribute("route", route_id);
            req.setAttribute("errorInput", true);
            req.getRequestDispatcher("/jsp/DeleteRoutePage.jsp").forward(req,resp);
        }
    }
}
