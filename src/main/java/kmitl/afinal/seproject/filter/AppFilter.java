package kmitl.afinal.seproject.filter;

import kmitl.afinal.seproject.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "AppFilter", urlPatterns = "/*")
public class AppFilter implements Filter {

    private ArrayList<String> urlList;

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();
        boolean allowedRequest = false;

        if (urlList.contains(url)) {
            allowedRequest = true;
        }

        if (!allowedRequest) {
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
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {
        urlList = new ArrayList<String>();
        urlList.add("/Login.jsp");
        urlList.add("/login");
        urlList.add("/Register.jsp");
        urlList.add("/register");
    }

}
