package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.manager.Authenticator;
import kmitl.afinal.seproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        String username, password;
        username = request.getParameter("username");
        password = request.getParameter("password");

        Authenticator authenticator = new Authenticator();

        PrintWriter out = response.getWriter();

        try {
            User user = authenticator.validate(username, password, connection);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                response.sendRedirect("Profile.jsp");
                out.println("Login success! " + user.getUsername() + ", create on " + user.getCreateTime());
            } else {
                response.sendRedirect("loginFailed.html");
                //out.println("Login failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
            //out.println("There is something wrong." + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
