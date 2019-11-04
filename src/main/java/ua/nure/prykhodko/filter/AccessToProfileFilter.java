package ua.nure.prykhodko.filter;

import ua.nure.prykhodko.entity.ROLE;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessToProfileFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if(session!=null
                && session.getAttribute("role") != null
                &&((ROLE)session.getAttribute("role")).equals(ROLE.USER)){
            req.getRequestDispatcher("/profile").forward(req,resp);
        }else if(session!=null
                && session.getAttribute("role") != null
                &&((ROLE)session.getAttribute("role")).equals(ROLE.ADMIN)){
            req.getRequestDispatcher("/admin").forward(req,resp);
        }else {
            resp.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
