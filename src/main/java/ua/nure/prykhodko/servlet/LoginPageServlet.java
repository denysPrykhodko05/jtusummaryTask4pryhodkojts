package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.entity.User;
import ua.nure.prykhodko.exception.Messages;
import ua.nure.prykhodko.utils.AccessController;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginPageServlet.class);


    @Override
    public void init() throws ServletException {
        log.info(Messages.INFO_ENTER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/LoginPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        UserDAO userDAO =(UserDAO) servletContext.getAttribute("userDAO");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("loginBool", false);

        req.setAttribute("login", login);
        req.setAttribute("password", password);
        req.setAttribute("error_bool", false);

        User user = null;
        if (Validation.isCorrectLogin(login)) {
            user = userDAO.findUserByLogin(login);
            log.trace(Messages.TRACE_FOUND_USER + user);
        }

        if (user == null) {
            req.setAttribute("error", "Invalid login");
            req.setAttribute("error_bool", true);
            req.getRequestDispatcher("/jsp/LoginPage.jsp").forward(req,resp);
        }else if (Validation.isCorrectPassword(password) && user.getPassword().equals(password)){
            ROLE role = userDAO.getRoleByID(user.getRoleId());
            log.trace(Messages.TRACE_FOUND_USER_ROLE+ role);
            session.setAttribute("login", login);
            log.trace(Messages.TRACE_SESSION_LOGIN + login);
            session.setAttribute("password", password);
            log.trace(Messages.TRACE_SESSION_PASSWORD+ password);
            session.setAttribute("role",role);
            log.trace(Messages.TRACE_SESSION_ROLE + role);
            session.setAttribute("loginBool", true);
            log.trace(Messages.TRACE_SESSION_LOGINBOOL + true);
            session.setAttribute("email", user.getEmail());
            log.trace(Messages.TRACE_SESSION_EMAIL + user.getEmail());
            session.setAttribute("count", user.getCount());
            log.trace(Messages.TRACE_SESSION_COUNT + user.getCount());

            AccessController.moveToMenu(req,resp,role);
            }else{
            req.setAttribute("error","Invalid password");
            req.setAttribute("error_bool", true);
            req.getRequestDispatcher("/jsp/LoginPage.jsp").forward(req,resp);
        }
    }
    @Override
    public void destroy() {
        log.info(Messages.INFO_EXIT);
    }
}
