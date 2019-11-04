package ua.nure.prykhodko.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessToBuyTicketFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if ((boolean)session.getAttribute("loginBool")){
            req.getRequestDispatcher("/trainWagon").forward(req,resp);
        }else{
            req.setAttribute("unLogin", false);
            req.getRequestDispatcher("/search").forward(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
