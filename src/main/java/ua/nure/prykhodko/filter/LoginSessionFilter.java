package ua.nure.prykhodko.filter;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.utils.AccessController;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;
@WebFilter(urlPatterns = {"/"})
public class   LoginSessionFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //LOG.trace("Request URI --> "+ resp.); ask how to log response
        final HttpSession session = req.getSession();
        session.setAttribute("locale","en");

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final ROLE role = (ROLE) session.getAttribute("role");
            AccessController.moveToMenu(req,resp,role);
        }else{
            AccessController.moveToMenu(req,resp,ROLE.UNKNOWN);
        }
    }

    public void destroy() {

    }


}
