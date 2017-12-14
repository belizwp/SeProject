package kmitl.afinal.seproject.filter;

import kmitl.afinal.seproject.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "AppFilter",
        urlPatterns = {"/index.jsp", "/Profile.jsp", "/Search.jsp", "/sheet.jsp", "/subject.jsp", "/upload.jsp"},
        servletNames = {"InfoCollectorServlet", "SearchServlet", "SearchSubjectServlet",
                "UploadServlet", "viewSheetAllServlet", "ViewSheetMeServlet"})
public class AppFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (user == null) {
                throw new Exception("need logged in");
            }
        } catch (Exception e) {
            response.sendRedirect("/Login.jsp");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
