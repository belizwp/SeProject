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

@WebServlet(name = "ViewSheetMeServlet", urlPatterns = "/viewSheetMe")
public class ViewSheetMe extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html");

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            List<Sheet> sheetList = SheetDao.with(connection).getSheet(user.getUsername());

            PrintWriter out = response.getWriter();
            for (Sheet sheet : sheetList) {
                out.println(sheet);
            }
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
