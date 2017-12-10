package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.manager.Authenticator;
import kmitl.afinal.seproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String username, password, fname, lname, email;
        username = request.getParameter("username");
        password = request.getParameter("password");
        fname = request.getParameter("fname");
        lname = request.getParameter("lname");
        email = request.getParameter("email");

        Authenticator authenticator = new Authenticator();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFname(fname);
        user.setLname(lname);
        user.setEmail(email);

        PrintWriter out = response.getWriter();

        try {
            if (authenticator.register(user)) {
                out.println("Register success!");
            } else {
                out.println("Register failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("There is something wrong." + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
