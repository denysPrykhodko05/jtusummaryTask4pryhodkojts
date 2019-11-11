package ua.nure.prykhodko.servlet;

import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;

public class RouteEditServletTest extends Mockito {

    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn("startStation");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet2() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("");
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet3() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn(null);
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet4() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn("finalStation");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet5() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn("deleteStation");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet6() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn("addStation");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet7() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(routeDAO.getEntityById(2)).thenReturn(new Route());
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn("train");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    @Test
    public void doGet8() throws ServletException, IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);

        when(routeDAO.getEntityById(2)).thenReturn(null);
        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("choice")).thenReturn("train");
        when(routeDAO.isExistRoute(2)).thenReturn(true);
        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new RouteEditServlet().doGet(req, resp);
    }

    //Update train and check time
    @Test
    public void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn("kyiv");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("03:00");
        when(routeDAO.updateTrainInRoute(2, 1)).thenReturn(true);

        new RouteEditServlet().doPost(req, resp);
    }

    //add station true
    @Test
    public void doPost2() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn("kyiv");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");

        when(stationDAO.getStationByName("kyiv")).thenReturn(new Station());

        new RouteEditServlet().doPost(req,resp);
    }
    @Test
    public void doPost3() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn("kyiv");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");

        when(stationDAO.getStationByName("kyiv")).thenReturn(null);

        new RouteEditServlet().doPost(req,resp);
    }

    @Test
    public void doPost4() throws ServletException, IOException {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");

        when(stationDAO.getStationByName("kharkiv")).thenReturn(new Station());

        new RouteEditServlet().doPost(req,resp);
    }

    @Test
    public void doPost5() throws ServletException, IOException {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");

        when(stationDAO.getStationByName("kharkiv")).thenReturn(null);

        new RouteEditServlet().doPost(req,resp);
    }
    @Test
    public void doPost6() throws ServletException, IOException {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn(null);
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");
        when(req.getParameter("stationName")).thenReturn("kyiv");

        new RouteEditServlet().doPost(req,resp);
    }

    @Test
    public void doPost7() throws ServletException, IOException {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn(null);
        when(req.getParameter("deleteStation")).thenReturn(null);
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");
        when(req.getParameter("stationName")).thenReturn("kyiv");

        new RouteEditServlet().doPost(req,resp);
    }

    @Test
    public void doPost8() throws ServletException, IOException {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn(null);
        when(req.getParameter("deleteStation")).thenReturn(null);
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");
        when(req.getParameter("stationName")).thenReturn(null);

        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);

        new RouteEditServlet().doPost(req,resp);
    }

    @Test
    public void doPost9() throws ServletException, IOException {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn(null);
        when(req.getParameter("finalName")).thenReturn(null);
        when(req.getParameter("deleteStation")).thenReturn(null);
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");
        when(req.getParameter("stationName")).thenReturn("kyiv");

        when(stationDAO.getEntityID("kyiv")).thenReturn(2);

        new RouteEditServlet().doPost(req,resp);
    }
}

/*
    @Test
  public void doPost() {
        HttpServletRequest req= mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        RouteDAO routeDAO = mock(RouteDAO.class);
        StationDAO stationDAO = mock(StationDAO.class);

        when(req.getServletContext()).thenReturn(context);
        when(context.getAttribute("routeDAO")).thenReturn(routeDAO);
        when(context.getAttribute("stationDAO")).thenReturn(stationDAO);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("route_number")).thenReturn("2");
        when(req.getParameter("startName")).thenReturn("kyiv");
        when(req.getParameter("finalName")).thenReturn("kharkiv");
        when(req.getParameter("deleteStation")).thenReturn("deleteStation");
        when(req.getParameter("addStation")).thenReturn("addStation");
        when(req.getParameter("departDate")).thenReturn("2019-11-11");
        when(req.getParameter("departTime")).thenReturn("00:00");
        when(req.getParameter("arriveDate")).thenReturn("2019-11-11");
        when(req.getParameter("arriveTime")).thenReturn("00:00");

        new RouteEditServlet().doPost(req,resp);
    }
 */