package ua.nure.prykhodko.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/logout"})
public class LogoutFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("role");
        session.removeAttribute("loginBool");
        session.removeAttribute("email");
        session.removeAttribute("count");
        response.sendRedirect("/");
    }

    @Override
    public void destroy() {

    }
}
