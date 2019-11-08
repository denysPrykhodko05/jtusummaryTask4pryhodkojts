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
        boolean respFlag=false;
        boolean isLogin=false;
        try{
            isLogin =(boolean) session.getAttribute("loginBool");
        }catch (NullPointerException e){
            req.setAttribute("unLogin", false);
            resp.sendRedirect("/login");
            respFlag=true;
            e.printStackTrace();
        }
        if (isLogin){
            filterChain.doFilter(req,resp);
        }else{
            req.setAttribute("unLogin", false);
            if (!respFlag) {
                resp.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
