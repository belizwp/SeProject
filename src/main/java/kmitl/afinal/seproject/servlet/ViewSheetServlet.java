package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.SheetDao;
import kmitl.afinal.seproject.dao.SheetPdfDao;
import kmitl.afinal.seproject.model.Sheet;
import kmitl.afinal.seproject.model.SheetPdf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ViewSheetServlet", urlPatterns = "/viewSheet")
public class ViewSheetServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Connection connection = (Connection) getServletContext().getAttribute("connection");

        int id = parseInt(request.getParameter("id"));

        if (id > 0) {
            try {
                Sheet sheet = SheetDao.with(connection).getSheet(id);
                SheetPdf sheetPdf = SheetPdfDao.with(connection).getWithSheetId(id);

                request.setAttribute("sheet", sheet);
                request.setAttribute("pdf", sheetPdf);
                request.getRequestDispatcher("test/sheet.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
