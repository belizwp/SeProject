package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.SheetDao;
import kmitl.afinal.seproject.model.BaseModel;
import kmitl.afinal.seproject.model.Sheet;
import kmitl.afinal.seproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Template", urlPatterns = "/viewsheetme")
public class ViewSheetMe extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html");

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        String user = (String) request.getSession().getAttribute("user");
        List<Sheet> sheet1 = SheetDao.instance.getSheet(user);

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
