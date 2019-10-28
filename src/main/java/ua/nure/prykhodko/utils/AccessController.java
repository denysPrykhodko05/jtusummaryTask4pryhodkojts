package ua.nure.prykhodko.utils;

import ua.nure.prykhodko.entity.ROLE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessController {
    public static void moveToMenu(HttpServletRequest req, HttpServletResponse resp, ROLE role) {
        try {
            if (role.equals(ROLE.ADMIN)) {
                resp.sendRedirect("/admin");
            }else if(role.equals(ROLE.USER)){
                req.getRequestDispatcher("/jsp/UserPage.jsp").forward(req,resp);
            }else{
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
