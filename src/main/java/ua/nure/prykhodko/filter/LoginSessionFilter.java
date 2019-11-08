package ua.nure.prykhodko.filter;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.utils.AccessController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class    LoginSessionFilter implements Filter {

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
            AccessController.moveToMenu(req,resp,role);
        }else{
            AccessController.moveToMenu(req,resp,ROLE.UNKNOWN);
        }
        LOG.debug("Filter finished");
    }

    public void destroy() {

    }


}
