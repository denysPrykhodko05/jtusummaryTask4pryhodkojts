package ua.nure.prykhodko.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StationEditFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final String command = req.getParameter("command");

            switch (command) {
                case "Edit station":
                    req.getRequestDispatcher("/jsp/EditStationPage.jsp").forward(req, resp);
                    break;

                case "Add station":
                    req.getRequestDispatcher("/jsp/AddStationPage.jsp").forward(req,resp);
                    break;

                case "Delete station":
                    req.getRequestDispatcher("/jsp/DeleteStationPage.jsp").forward(req,resp);
                    break;

                case "Edit route":
                    req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req,resp);
                    break;

                case "Add route":
                    resp.sendRedirect("/admin/routeAdd");
                    break;

                case "Delete route":
                    req.getRequestDispatcher("/jsp/DeleteRoutePage.jsp").forward(req,resp);
                    break;
            }
        }

    @Override
    public void destroy() {

    }
}
