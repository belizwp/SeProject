package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.BranchDao;
import kmitl.afinal.seproject.dao.DepartmentDao;
import kmitl.afinal.seproject.dao.SubjectDao;
import kmitl.afinal.seproject.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SelectServlet", urlPatterns = "/getChoice")
public class SelectServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String type = request.getParameter("type");
        int id = partseInt(request.getParameter("id"));
        int semester = partseInt(request.getParameter("semester"));
        String text = request.getParameter("text");

        List<BaseModel> list;
        PrintWriter out = response.getWriter();

        try {
            Connection connection = (Connection) getServletContext().getAttribute("connection");

            if (text != null) {
                printFirstOption(0, text, out);
            } else {
                printFirstOption(0, "--ไม่ระบุ--", out);
            }

            if ("department".equals(type)) {
                list = DepartmentDao.with(connection).getWithFacultyId(id);
            } else if ("branch".equals(type)) {
                list = BranchDao.with(connection).getWithDepartmentId(id);
            } else if ("subject".equals(type)) {
                list = SubjectDao.with(connection).getWithBranchId(id);
            } else if ("year".equals(type)) {
                list = new ArrayList<BaseModel>();
                int maxYear = BranchDao.with(connection).getMaxYear(id);
                for (int i = 1; i <= maxYear; i++) {
                    list.add(new Year(i));
                }
            } else if ("sub-year".equals(type) || "sub-semester".equals(type)) {
                int year = partseInt(request.getParameter("year"));
                list = SubjectDao.with(connection).getWithBranchIdAndYear(id, year);
            } else {
                list = new ArrayList<BaseModel>();
            }

            printChoice(type, list, out, semester);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printChoice(String type, List<BaseModel> list, PrintWriter out, int semester) {
        if ("department".equals(type)) {
            for (BaseModel model : list) {
                Department department = (Department) model;
                printOption(department.getId(), department.getName(), out);
            }
        } else if ("branch".equals(type)) {
            for (BaseModel model : list) {
                Branch branch = (Branch) model;
                printOption(branch.getId(), branch.getName(), out);
            }
        } else if ("subject".equals(type) || "sub-year".equals(type) || "sub-semester".equals(type)) {
            for (BaseModel model : list) {
                Subject subject = (Subject) model;
                if (semester > 0) {
                    if (subject.getSemester() == semester) {
                        printOption(subject.getId(), subject.getName(), out);
                    }
                } else {
                    printOption(subject.getId(), subject.getName(), out);
                }
            }
        } else if ("year".equals(type)) {
            for (BaseModel model : list) {
                Year year = (Year) model;
                printOption(year.getYear(), year.getYear(), out);
            }
        }
    }

    private void printFirstOption(int value, String name, PrintWriter out) {
        out.println("<option selected disabled value=''>" + name + "</option>");
    }

    private void printOption(int value, String name, PrintWriter out) {
        out.println("<option value='" + value + "'>" + name + "</option>");
    }

    private void printOption(String value, String name, PrintWriter out) {
        out.println("<option value='" + value + "'>" + name + "</option>");
    }

    private void printOption(int value, int name, PrintWriter out) {
        out.println("<option value='" + value + "'>" + name + "</option>");
    }

    private int partseInt(String number) {
        try {
            return Integer.parseInt(number);
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
