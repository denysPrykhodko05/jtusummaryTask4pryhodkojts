package ua.nure.prykhodko.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeLocaleServletTest {

    @Test
    public void doPost() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        HttpServletResponse resp=mock(HttpServletResponse.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getParameter("locale")).thenReturn("ru");
        when(req.getSession()).thenReturn(session);

        new ChangeLocaleServlet().doPost(req,resp);
    }
}