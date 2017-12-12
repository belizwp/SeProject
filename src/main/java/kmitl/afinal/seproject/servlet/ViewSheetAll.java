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

@WebServlet(name = "viewSheetAllServlet", urlPatterns = "/viewSheetAll")
public class ViewSheetAll extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        List<Sheet> sheetList = SheetDao.with(connection).getAll();

        PrintWriter out = response.getWriter();
        for (Sheet sheet : sheetList) {
            out.println(sheet);
        }
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
