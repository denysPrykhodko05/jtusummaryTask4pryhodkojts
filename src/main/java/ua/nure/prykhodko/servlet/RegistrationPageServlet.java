package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.entity.User;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationPageServlet extends HttpServlet {
    UserDAO userDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/RegistrationPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = null;
        User user = new User();

        req.setAttribute("error_bool", false);

        final String login = req.getParameter("login");
        req.setAttribute("login", login);

        final String password= req.getParameter("password");
        req.setAttribute("password", password);

        final String email = req.getParameter("email");
        req.setAttribute("email",email);

        final String confirmPassword = req.getParameter("confirmPassword");
        req.setAttribute("confirmPassword", confirmPassword);

        if(!Validation.isCorrectEmail(email)){
            req.setAttribute("error_bool", true);
            req.setAttribute("error", "Incorrect email");
            req.getRequestDispatcher("/jsp/RegistrationPage.jsp").forward(req,resp);
        }else if (!Validation.isCorrectLogin(login)){
            req.setAttribute("error_bool", true);
            req.setAttribute("error", "Incorrect login");
            req.getRequestDispatcher("/jsp/RegistrationPage.jsp").forward(req,resp);
        }else if (!Validation.isCorrectPassword(password)){
            req.setAttribute("error_bool", true);
            req.setAttribute("error", "Incorrect password");
            req.getRequestDispatcher("/jsp/RegistrationPage.jsp").forward(req,resp);
        }else{
            user.setLogin(login);
            user.setPassword(password);
            user.setRoleId(2);
            user.setEmail(email);
            if (userDAO.addEntity(user)){
                session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                ROLE role = userDAO.getRoleByID(user.getRoleId());
                session.setAttribute("role", role);
                session.setAttribute("email",email);
                session.setAttribute("loginBool", true);
                session.setAttribute("count",0);
                resp.sendRedirect("/login");
            }else{
                req.setAttribute("error_bool", true);
                req.setAttribute("error", "User with this login/email is already exist");
                req.getRequestDispatcher("/jsp/RegistrationPage.jsp").forward(req,resp);
            }

        }

    }

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
}
