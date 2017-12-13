package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.SubjectDao;
import kmitl.afinal.seproject.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SearchSubjectServlet", urlPatterns = "/subject")
public class SearchSubjectServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        int branch = Integer.parseInt(request.getParameter("branch"));
        int year = Integer.parseInt(request.getParameter("year"));

        try {
            List<Subject> first = SubjectDao.with(connection).search(branch, year, 1);
            List<Subject> second = SubjectDao.with(connection).search(branch, year, 2);

            request.setAttribute("first", first);
            request.setAttribute("second", second);
            request.getRequestDispatcher("subject.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
