package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.entity.User;
import ua.nure.prykhodko.utils.AccessController;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginPageServlet extends HttpServlet {
    UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/LoginPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        }

        if (user == null) {
            req.setAttribute("error", "Invalid login");
            req.setAttribute("error_bool", true);
            req.getRequestDispatcher("/jsp/LoginPage.jsp").forward(req,resp);
        }else if (Validation.isCorrectPassword(password) && user.getPassword().equals(password)){
            ROLE role = userDAO.getRoleByID(user.getRoleId());


            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("role",role);
            session.setAttribute("loginBool", true);

            AccessController.moveToMenu(req,resp,role);
            }else{
            req.setAttribute("error","Invalid password");
            req.setAttribute("error_bool", true);
            req.getRequestDispatcher("/jsp/LoginPage.jsp").forward(req,resp);
        }
    }

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
}
