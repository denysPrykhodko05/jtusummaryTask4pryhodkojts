package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.entity.User;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        HttpSession session = req.getSession();
        double count;
        String login;
        UserDAO userDAO = (UserDAO) servletContext.getAttribute("userDAO");
        login = (String)session.getAttribute("login");
        count = userDAO.getCountByLogin(login);
        session.setAttribute("count",count);
        req.getRequestDispatcher("/jsp/UserProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        UserDAO userDAO = (UserDAO) context.getAttribute("userDAO");
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        double countOld = userDAO.getCountByLogin(login);
        double countNew = 0;

        if (req.getAttribute("success") == null) {
            try {
                countNew = Double.parseDouble(req.getParameter("count"));
            } catch (NumberFormatException e) {
                req.setAttribute("errorProfile", "Incorrect input. Sum must be more than 0 and less then 1000000");
                req.getRequestDispatcher("/jsp/UserProfile.jsp").forward(req, resp);
            } catch (NullPointerException e) {
                req.getRequestDispatcher("/jsp/UserProfile.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/jsp/UserProfile.jsp").forward(req, resp);
        }

        if (Validation.fundCountValidation(Double.toString(countNew)) && countNew > 0 && countNew < 1000000) {
            countNew += countOld;
            if (userDAO.updateCountByLogin(countNew, login)) {
                session.setAttribute("count", countNew);
               resp.sendRedirect("/profile");
            } else {
                doGet(req, resp);
            }
        } else {
            req.setAttribute("errorProfile", "Incorrect input. Sum must be more than 0 and less then 1000000");
            req.getRequestDispatcher("/jsp/UserProfile.jsp").forward(req, resp);
        }
    }
}
