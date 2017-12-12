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

@WebServlet(name = "Template", urlPatterns = "/viewsheetme")
public class ViewSheetAll extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        SheetDao sheet = new SheetDao();

        List<Sheet> sheet1 = sheet.getAll();

        //request.setAttribute("sheet", sheet1);
        //request.getRequestDispatcher("/test/index.jsp").forward(request, response);

        PrintWriter out = response.getWriter();
        out.println(sheet1);
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
