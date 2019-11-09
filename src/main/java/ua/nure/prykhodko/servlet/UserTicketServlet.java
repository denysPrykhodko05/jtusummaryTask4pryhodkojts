package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.TicketDAO;
import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.bean.BoughtTicket;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile/ticket")
public class UserTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        HttpSession session = req.getSession();
        UserDAO userDAO = (UserDAO) servletContext.getAttribute("userDAO");
        TicketDAO ticketDAO = (TicketDAO) servletContext.getAttribute("ticketDAO");
        final String login = (String) session.getAttribute("login");
        BoughtTicket bt = new BoughtTicket();

        int ticket_id = userDAO.getTicketId(login);

        bt = ticketDAO.getTicketById(ticket_id);

        req.setAttribute("ticket", bt);
        req.getRequestDispatcher("/jsp/TicketPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
