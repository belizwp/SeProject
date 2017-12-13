package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.SheetDao;
import kmitl.afinal.seproject.dao.SubjectDao;
import kmitl.afinal.seproject.model.Sheet;
import kmitl.afinal.seproject.model.SubjectView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "viewSheetAllServlet", urlPatterns = "/viewSheetAll")
public class ViewSheetAll extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html");

        int subjectId = Integer.parseInt(request.getParameter("subject_id"));

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        List<Sheet> sheetList = SheetDao.with(connection).getSheetBySubjectId(subjectId);
        SubjectView subjectView = SubjectDao.with(connection).getSubjectView(subjectId);

        request.setAttribute("sheetList", sheetList);
        request.setAttribute("subjectView", subjectView);
        request.getRequestDispatcher("sheet.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
