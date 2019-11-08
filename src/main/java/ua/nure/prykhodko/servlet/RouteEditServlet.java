package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.bean.Route;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.entity.Train;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/routeEdit")
public class RouteEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String route_number = req.getParameter("route_number");
        final String choice = req.getParameter("choice");
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");

        if (choice == null) {
            req.setAttribute("errorNothingChosen", true);
            req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
        }

        switch (choice) {
            case "startStation":
                if (!route_number.equals("")) {
                    req.setAttribute("input", true);
                    req.setAttribute("startStation", true);
                    req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorIncorrectRouteInput", true);
                    req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                }
                break;

            case "finalStation":
                req.setAttribute("input", true);
                req.setAttribute("finalStation", true);
                req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                break;

            case "train":
                if (route_number != null && !route_number.equals("")) {
                    Route route = routeDAO.getEntityById(Integer.parseInt(route_number));
                    if (route != null) {
                        List<Train> availableTrain = routeDAO.getAvailableTrain();
                        req.setAttribute("trainList", availableTrain);
                        req.setAttribute("input", true);
                        req.setAttribute("train", true);
                        req.setAttribute("route_number", route_number);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorFindRoute", true);
                        req.setAttribute("route_number", route_number);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("errorIncorrectRouteInput", true);
                    req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                }
                break;
            default:
                req.setAttribute("errorNothingChosen", true);
                req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        final String train_id = req.getParameter("id");
        final String route_id = req.getParameter("route_number");

        if (!train_id.equals("") && !route_id.equals("") && routeDAO.updateTrainInRoute(Integer.parseInt(route_id), Integer.parseInt(train_id))) {
            resp.sendRedirect("/admin");
        } else {
            req.setAttribute("errorUpdate", true);
            req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
        }

    }
}
