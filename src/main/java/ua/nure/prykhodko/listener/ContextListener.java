package ua.nure.prykhodko.listener;

import ua.nure.prykhodko.dao.SqlDAO.*;
import ua.nure.prykhodko.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        RouteDAO routeDAO = new RouteDAO();
        UserDAO userDAO = new UserDAO();
        StationDAO stationDAO = new StationDAO();
        TrainDAO trainDAO = new TrainDAO();
        TicketDAO ticketDAO = new TicketDAO();
        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("userDAO", userDAO);
        servletContext.setAttribute("routeDAO", routeDAO);
        servletContext.setAttribute("stationDAO", stationDAO);
        servletContext.setAttribute("trainDAO", trainDAO);
        servletContext.setAttribute("ticketDAO", ticketDAO);
        servletContext.setAttribute("lang","en");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
