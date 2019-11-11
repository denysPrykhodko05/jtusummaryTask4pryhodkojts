package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.TicketDAO;
import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.bean.BoughtTicket;
import ua.nure.prykhodko.exception.Messages;

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
    private static final Logger log = Logger.getLogger(UserTicketServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        HttpSession session = req.getSession();
        UserDAO userDAO = (UserDAO) servletContext.getAttribute("userDAO");
        TicketDAO ticketDAO = (TicketDAO) servletContext.getAttribute("ticketDAO");
        final String login = (String) session.getAttribute("login");
        BoughtTicket bt;

        int ticket_id = userDAO.getTicketId(login);

        bt = ticketDAO.getTicketById(ticket_id);
        log.trace(Messages.TRACE_TICKET_FOUND+bt);

        req.setAttribute("ticket", bt);
        req.getRequestDispatcher("/jsp/TicketPage.jsp").forward(req,resp);
    }
}
