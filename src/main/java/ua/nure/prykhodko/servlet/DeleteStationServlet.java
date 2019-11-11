package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.exception.Messages;
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
    private static final Logger log = Logger.getLogger(DeleteStationServlet.class);

    @Override
    public void init() throws ServletException {
        log.info(Messages.INFO_ENTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");
        final String name = req.getParameter("station_name");
        int id = 0;

        if (Validation.isCorrectStationName(name)) {
            id = stationDAO.getEntityID(name);
            log.trace(Messages.TRACE_STATION_FOUND+id);
            if (id > 0  && stationDAO.delete(id)) {
                log.trace(Messages.TRACE_STATION_DELETE+id);
                req.setAttribute("success", true);
                req.getRequestDispatcher("/jsp/DeleteStationPage.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", true);
                req.setAttribute("station_name",name);
                req.getRequestDispatcher("/jsp/DeleteStationPage.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", true);
            req.getRequestDispatcher("/jsp/DeleteStationPage.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        log.info(Messages.INFO_EXIT);
    }

}
