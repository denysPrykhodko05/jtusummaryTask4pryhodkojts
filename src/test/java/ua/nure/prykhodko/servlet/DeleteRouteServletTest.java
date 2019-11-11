package ua.nure.prykhodko.servlet;

import org.junit.Test;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteRouteServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getServletContext()).thenReturn(context);
        when(req.getParameter("route")).thenReturn("2");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(routeDAO.deleteRouteFromStationRoute(2)).thenReturn(true);
        when(routeDAO.delete(2)).thenReturn(true);
        new DeleteRouteServlet().doPost(req, resp);
    }

    @Test
    public void doPost2() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        ServletContext context = mock(ServletContext.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getServletContext()).thenReturn(context);
        when(req.getParameter("route")).thenReturn("2");
        when(routeDAO.isExistRoute(2)).thenReturn(false);
        when(routeDAO.deleteRouteFromStationRoute(2)).thenReturn(true);
        when(routeDAO.delete(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/DeleteRoutePage.jsp")).thenReturn(dispatcher);
        new DeleteRouteServlet().doPost(req, resp);

    }
}