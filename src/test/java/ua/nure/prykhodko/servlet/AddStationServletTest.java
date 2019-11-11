package ua.nure.prykhodko.servlet;

import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.entity.Station;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

public class AddStationServletTest extends Mockito {

    @Test
    public void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        StationDAO stationDAO = mock(StationDAO.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        Station station = new Station();
        when(req.getServletContext()).thenReturn(servletContext);
        servletContext.setAttribute("stationDAO",stationDAO);
        servletContext.setAttribute("routeDAO",routeDAO);
        when(req.getParameter("name")).thenReturn("nejuun");
        when(req.getParameter("depart_date")).thenReturn("2019-11-11");
        when(req.getParameter("depart_time")).thenReturn("00:00");
        when(req.getParameter("arrive_date")).thenReturn("2019-11-11");
        when(req.getParameter("arrive_time")).thenReturn("03:00");
        when(req.getParameter("route")).thenReturn("2");
        when(servletContext.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(servletContext.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(stationDAO.getEntityID("nejuun")).thenReturn(0);

        when(req.getRequestDispatcher("/jsp/AddStationPage.jsp")).thenReturn(dispatcher);
        new AddStationServlet().doPost(req,resp);
    }

    @Test
    public void doPost2() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        StationDAO stationDAO = mock(StationDAO.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        Station station = new Station();
        when(req.getServletContext()).thenReturn(servletContext);
        servletContext.setAttribute("stationDAO",stationDAO);
        servletContext.setAttribute("routeDAO",routeDAO);
        when(req.getParameter("depart_date")).thenReturn("2019-11-11");
        when(req.getParameter("depart_time")).thenReturn("00:00");
        when(req.getParameter("arrive_date")).thenReturn("2019-11-11");
        when(req.getParameter("arrive_time")).thenReturn("03:00");
        when(req.getParameter("route")).thenReturn("");
        when(servletContext.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(servletContext.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(stationDAO.getEntityID("nejuun")).thenReturn(0);

        when(req.getRequestDispatcher("/jsp/AddStationPage.jsp")).thenReturn(dispatcher);
        new AddStationServlet().doPost(req,resp);
    }

    @Test
    public void doPost3() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        StationDAO stationDAO = mock(StationDAO.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        Station station = new Station();
        when(req.getServletContext()).thenReturn(servletContext);
        servletContext.setAttribute("stationDAO",stationDAO);
        servletContext.setAttribute("routeDAO",routeDAO);
        when(req.getParameter("name")).thenReturn("nejuun");
        when(req.getParameter("depart_date")).thenReturn("2019-11-11");
        when(req.getParameter("depart_time")).thenReturn("03:00");
        when(req.getParameter("arrive_date")).thenReturn("2019-11-11");
        when(req.getParameter("arrive_time")).thenReturn("00:00");
        when(req.getParameter("route")).thenReturn("2");
        when(servletContext.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(servletContext.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(stationDAO.getEntityID("nejuun")).thenReturn(0);

        when(req.getRequestDispatcher("/jsp/AddStationPage.jsp")).thenReturn(dispatcher);
        new AddStationServlet().doPost(req,resp);
    }
    @Test
    public void doPost4() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        StationDAO stationDAO = mock(StationDAO.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        Station station = new Station();
        when(req.getServletContext()).thenReturn(servletContext);
        servletContext.setAttribute("stationDAO",stationDAO);
        servletContext.setAttribute("routeDAO",routeDAO);
        when(req.getParameter("name")).thenReturn(null);
        when(req.getParameter("depart_date")).thenReturn("2019-11-11");
        when(req.getParameter("depart_time")).thenReturn("00:00");
        when(req.getParameter("arrive_date")).thenReturn("2019-11-11");
        when(req.getParameter("arrive_time")).thenReturn("03:00");
        when(req.getParameter("route")).thenReturn("2");
        when(servletContext.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(servletContext.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(stationDAO.getEntityID("nejuun")).thenReturn(0);

        when(req.getRequestDispatcher("/jsp/AddStationPage.jsp")).thenReturn(dispatcher);
        new AddStationServlet().doPost(req,resp);
    }
    @Test
    public void doPost5() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        StationDAO stationDAO = mock(StationDAO.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        Station station = new Station();
        when(req.getServletContext()).thenReturn(servletContext);
        servletContext.setAttribute("stationDAO",stationDAO);
        servletContext.setAttribute("routeDAO",routeDAO);
        when(req.getParameter("name")).thenReturn("kyiv");
        when(req.getParameter("depart_date")).thenReturn("2019-11-11");
        when(req.getParameter("depart_time")).thenReturn("00:00");
        when(req.getParameter("arrive_date")).thenReturn("2019-11-11");
        when(req.getParameter("arrive_time")).thenReturn("03:00");
        when(req.getParameter("route")).thenReturn("2");
        when(servletContext.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(servletContext.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);

        when(req.getRequestDispatcher("/jsp/AddStationPage.jsp")).thenReturn(dispatcher);
        new AddStationServlet().doPost(req,resp);
    }

}