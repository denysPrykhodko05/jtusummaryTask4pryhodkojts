package ua.nure.prykhodko.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminPageServletTest {

    @Test
    public void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp =mock(HttpServletResponse.class);
        RequestDispatcher dispatcher=mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/jsp/AdminPage.jsp")).thenReturn(dispatcher);
        new AdminPageServlet().doGet(req,resp);
    }
}