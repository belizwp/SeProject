package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.SheetDao;
import kmitl.afinal.seproject.model.Sheet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        String title = request.getParameter("title");
        String faculty = request.getParameter("faculty");
        String department = request.getParameter("department");
        String branch = request.getParameter("branch");
        String subject = request.getParameter("subject");

        Sheet sheet = new Sheet();
        sheet.setTitle(title.trim().equals("") ? null : title);
        sheet.setFaculty_id(faculty.equals("0") ? null : Integer.parseInt(faculty));
        sheet.setDepartment_id(department.equals("0") ? null : Integer.parseInt(department));
        sheet.setBranch_id(branch.equals("0") ? null : Integer.parseInt(branch));
        sheet.setSubject_id(subject.equals("0") ? null : subject);

        try {
            List<Sheet> result = SheetDao.with(connection).search(sheet);

//            PrintWriter out = response.getWriter();
//
//            out.println("found " + result.size() + " sheet<br>");
//
//            for (Sheet s : result) {
//                out.println(s.getTitle() + " | create by " + s.getCreate_by() + "<br>");
//            }

            request.setAttribute("result", result);
            request.getRequestDispatcher("test/result.jsp").forward(request, response);

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
