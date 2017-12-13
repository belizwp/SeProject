package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.model.SubjectView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InfoCollectorServlet", urlPatterns = "/collector")
public class UploadInfoCollectorServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String subjectId = request.getParameter("subjectId");
        String branchId = request.getParameter("branchId");
        String departmentId = request.getParameter("departmentId");
        String facultyId = request.getParameter("facultyId");
        String semester = request.getParameter("semester");
        String year = request.getParameter("year");

        SubjectView subjectView = new SubjectView();
        subjectView.setId(subjectId);
        subjectView.setBranchId(Integer.valueOf(branchId));
        subjectView.setDepartmentId(Integer.valueOf(departmentId));
        subjectView.setFacultyId(Integer.valueOf(facultyId));
        subjectView.setSemester(Integer.parseInt(semester));
        subjectView.setYear(Integer.parseInt(year));

        request.setAttribute("subjectView", subjectView);
        request.getRequestDispatcher("upload.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
