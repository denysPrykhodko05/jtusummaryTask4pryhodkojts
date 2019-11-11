package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.exception.Messages;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeLang")
public class ChangeLocaleServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ChangeLocaleServlet.class);


    @Override
    public void init() throws ServletException {
        log.trace(Messages.INFO_ENTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String locale = req.getParameter("locale");

        session.setAttribute("locale",locale);
        log.trace(Messages.TRACE_SESSION_LOCALE + locale);

        resp.sendRedirect("/");
    }

    @Override
    public void destroy() {
        log.trace(Messages.INFO_EXIT);
    }

}
