package ua.nure.prykhodko.filter;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StationEditFilterTest {

    @Test
    public void doFilter() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getParameter("command")).thenReturn("Edit station");

        when(req.getRequestDispatcher("/jsp/EditStationPage.jsp")).thenReturn(dispatcher);
        new StationEditFilter().doFilter(req,resp,filterChain);
    }

    @Test
    public void doFilter2() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getParameter("command")).thenReturn("Add station");

        when(req.getRequestDispatcher("/jsp/AddStationPage.jsp")).thenReturn(dispatcher);
        new StationEditFilter().doFilter(req,resp,filterChain);
    }

    @Test
    public void doFilter3() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getParameter("command")).thenReturn("Delete station");

        when(req.getRequestDispatcher("/jsp/DeleteStationPage.jsp")).thenReturn(dispatcher);
        new StationEditFilter().doFilter(req,resp,filterChain);
    }

    @Test
    public void doFilter4() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getParameter("command")).thenReturn("Edit route");

        when(req.getRequestDispatcher("/jsp/EditRoutePage.jsp")).thenReturn(dispatcher);
        new StationEditFilter().doFilter(req,resp,filterChain);
    }

    @Test
    public void doFilter5() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getParameter("command")).thenReturn("Add route");
        resp.sendRedirect("/admin/routeAdd");
        new StationEditFilter().doFilter(req,resp,filterChain);
    }

    @Test
    public void doFilter6() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getParameter("command")).thenReturn("Delete route");

        when(req.getRequestDispatcher("/jsp/DeleteRoutePage.jsp")).thenReturn(dispatcher);
        new StationEditFilter().doFilter(req,resp,filterChain);
    }
}