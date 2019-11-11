package ua.nure.prykhodko.servlet;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.entity.Train;
import ua.nure.prykhodko.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AddRouteServletTest extends Mockito {

    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);

        RouteDAO routeDAO = (RouteDAO) mock(RouteDAO.class);

        List<Train> list = new ArrayList<>();
        list.add(new Train(8, 8, 8, 8));

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(routeDAO.getAvailableTrain()).thenReturn(list);
        when(req.getRequestDispatcher("/jsp/AddRoutePage.jsp")).thenReturn(dispatcher);

        new AddRouteServlet().doGet(req, response);
        verify(dispatcher).forward(req, response);
    }

    @Test
    public void doPost() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        Route route = new Route();

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        context.setAttribute("stationDAO", stationDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("startName")).thenReturn("kyiv");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("03:00");
        when(req.getParameter("id")).thenReturn("3");
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);
        when(stationDAO.getEntityID("kharkiv")).thenReturn(1);

        route.setStartPoint("kyiv");
        route.setFinalPoint("kharkiv");
        route.setStartPoint_id(1);
        route.setArrive_time(TimeUtils.parseStringToTimestamp("2019-11-11", "03:00"));
        route.setDepart_time(TimeUtils.parseStringToTimestamp("2019-11-11", "00:00"));
        route.setStartPoint_id(2);
        route.setFinalPoint_id(1);
        route.setTrainId(4);
        String startStationName = "kyiv";
        when(routeDAO.addEntity(route)).thenReturn(true);
        when(stationDAO.getEntityID(startStationName)).thenReturn(2);
        when(routeDAO.getRouteIdByTrainId(1)).thenReturn(2);
        when(routeDAO.addStartStationInStationRoute(new Station(9, "kyiv"))).thenReturn(false);
        when(routeDAO.addFinalStationInRoute(new Station(10, "kharkiv"))).thenReturn(false);
        new AddRouteServlet().doPost(req, resp);
    }

    @Test
    public void doPost2() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Route route = new Route();

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        context.setAttribute("stationDAO", stationDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("startName")).thenReturn("");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("03:00");
        when(req.getParameter("id")).thenReturn("3");
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);
        when(stationDAO.getEntityID("kharkiv")).thenReturn(1);

        when(req.getRequestDispatcher("/jsp/AddRoutePage.jsp")).thenReturn(dispatcher);
        new AddRouteServlet().doPost(req, resp);
    }

    @Test
    public void doPost3() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Route route = new Route();

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        context.setAttribute("stationDAO", stationDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("startName")).thenReturn("kharkiv");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("finalName")).thenReturn("");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("03:00");
        when(req.getParameter("id")).thenReturn("3");
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);
        when(stationDAO.getEntityID("kharkiv")).thenReturn(1);

        when(req.getRequestDispatcher("/jsp/AddRoutePage.jsp")).thenReturn(dispatcher);
        new AddRouteServlet().doPost(req, resp);
    }

    @Test
    public void doPost4() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Route route = new Route();

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        context.setAttribute("stationDAO", stationDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("startName")).thenReturn("kharkiv");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("03:00");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("arriveDate")).thenReturn(null);
        when(req.getParameter("arriveTime")).thenReturn(null);
        when(req.getParameter("id")).thenReturn("3");
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);
        when(stationDAO.getEntityID("kharkiv")).thenReturn(1);

        when(req.getRequestDispatcher("/jsp/AddRoutePage.jsp")).thenReturn(dispatcher);
        new AddRouteServlet().doPost(req, resp);
    }

    @Test
    public void doPost5() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Route route = new Route();

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        context.setAttribute("stationDAO", stationDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("startName")).thenReturn("kharkiv");
        when(req.getParameter("departDate")).thenReturn(null);
        when(req.getParameter("departTime")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("03:00");
        when(req.getParameter("id")).thenReturn("3");
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);
        when(stationDAO.getEntityID("kharkiv")).thenReturn(1);

        when(req.getRequestDispatcher("/jsp/AddRoutePage.jsp")).thenReturn(dispatcher);
        new AddRouteServlet().doPost(req, resp);
    }

    @Test
    public void doPost6() throws ServletException, IOException {
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Route route = new Route();

        when(req.getServletContext()).thenReturn(context);
        context.setAttribute("routeDAO", routeDAO);
        context.setAttribute("stationDAO", stationDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("startName")).thenReturn("kharkiv");
        when(req.getParameter("departDate")).thenReturn(null);
        when(req.getParameter("departTime")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("03:00");
        when(req.getParameter("id")).thenReturn(null);
        when(stationDAO.getEntityID("kyiv")).thenReturn(2);
        when(stationDAO.getEntityID("kharkiv")).thenReturn(1);

        when(req.getRequestDispatcher("/jsp/AddRoutePage.jsp")).thenReturn(dispatcher);
        new AddRouteServlet().doPost(req, resp);
    }
}