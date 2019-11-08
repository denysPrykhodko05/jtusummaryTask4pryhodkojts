package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.bean.Route;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/stationEdit/delete")
public class DeleteStationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        final String name = req.getParameter("station_name");
        int id = 0;

        if (Validation.isCorrectStationName(name)) {
            id = stationDAO.getEntityID(name);
            if (id > 0 && stationDAO.delete(id)) {
                req.setAttribute("success", true);
                req.getRequestDispatcher("/jsp/DeleteStationPage.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", true);
            req.getRequestDispatcher("/jsp/DeleteStationPage.jsp").forward(req, resp);
        }
    }
}
