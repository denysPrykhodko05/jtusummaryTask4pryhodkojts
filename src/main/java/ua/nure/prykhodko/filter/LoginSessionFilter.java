package ua.nure.prykhodko.filter;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.entity.ROLE;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class LoginSessionFilter implements Filter {

    private Logger LOG = Logger.getLogger(LoginSessionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        LOG.debug("Filter initialization ends");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        LOG.trace("Request URI --> " + req.getRequestURI());
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //LOG.trace("Request URI --> "+ resp.); ask how to log response
        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final ROLE role = (ROLE) session.getAttribute("role");
            moveToMenu(req,resp,role);
        }else{
            moveToMenu(req,resp,ROLE.UNKNOWN);
        }
        LOG.debug("Filter finished");
    }

    public void destroy() {

    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, ROLE role) {
        try {
            if (role.equals(ROLE.ADMIN)) {
                req.getRequestDispatcher("/jsp/AdminPage.jsp").forward(req, resp);
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
