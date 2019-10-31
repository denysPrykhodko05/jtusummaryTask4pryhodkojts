package ua.nure.prykhodko.listener;

import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.dao.SqlDAO.TrainDAO;
import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        RouteDAO routeDAO = new RouteDAO();
        UserDAO userDAO = new UserDAO();
        StationDAO stationDAO = new StationDAO();
        TrainDAO trainDAO = new TrainDAO();

        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("userDAO", userDAO);
        servletContext.setAttribute("routeDAO", routeDAO);
        servletContext.setAttribute("stationDAO", stationDAO);
        servletContext.setAttribute("trainDAO", trainDAO);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
