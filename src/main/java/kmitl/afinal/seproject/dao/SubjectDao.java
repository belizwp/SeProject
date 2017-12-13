package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.BaseModel;
import kmitl.afinal.seproject.model.Subject;
import kmitl.afinal.seproject.model.SubjectView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {

    private static SubjectDao instance;

    public static SubjectDao with(Connection connection) {
        if (instance == null) {
            instance = new SubjectDao();
        }
        instance.connection = connection;
        return instance;
    }

    private Connection connection;

    private SubjectDao() {

    }

    public List<BaseModel> getAll() throws SQLException {
        String sql = "SELECT * FROM `subject` ";

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    public List<BaseModel> getWithBranchId(int id) throws SQLException {
        String sql = "SELECT * FROM `subject` WHERE branch_id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    public SubjectView getSubjectView(int subjectId) throws SQLException {
        String sql = "SELECT d1.id, d1.name, d1.year, d1.semester, d2.name as branch, d3.name as department, d4.name as faculty " +
                "FROM sedb.subject AS d1 " +
                "INNER JOIN sedb.branch AS d2 " +
                "ON  (d1.branch_id = d2.id) " +
                "INNER JOIN sedb.department AS d3 " +
                "ON (d2.department_id = d3.id) " +
                "INNER JOIN sedb.faculty AS d4 " +
                "ON (d3.faculty_id = d4.id) " +
                "WHERE d1.id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, subjectId);
        ResultSet rs = stm.executeQuery();

        SubjectView subjectView = new SubjectView();

        if (rs.next()) {
            subjectView.setId(rs.getString("id"));
            subjectView.setName(rs.getString("name"));
            subjectView.setYear(rs.getInt("year"));
            subjectView.setSemester(rs.getInt("semester"));
            subjectView.setFacultyName(rs.getString("faculty"));
            subjectView.setDepartmentName(rs.getString("department"));
            subjectView.setBranchName(rs.getString("branch"));
        }

        return subjectView;
    }

    public List<Subject> search(int branchId, int year, int semester) throws SQLException {
        String sql = "SELECT * FROM `subject` WHERE branch_id = ? AND `year` = ? AND semester = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, branchId);
        stm.setInt(2, year);
        stm.setInt(3, semester);
        ResultSet rs = stm.executeQuery();

        List<Subject> list = new ArrayList<Subject>();

        fillSubject(rs, list);

        return list;
    }

    public List<BaseModel> getWithBranchIdAndYear(int id, int year) throws SQLException {
        if (year == 0) {
            return getWithBranchId(id);
        }

        String sql = "SELECT * FROM `subject` WHERE branch_id = ? AND `year` = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        stm.setInt(2, year);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    private void fillModel(ResultSet rs, List<BaseModel> out) throws SQLException {
        while (rs.next()) {
            Subject subject = new Subject();

            subject.setId(rs.getString("id"));
            subject.setName(rs.getString("name"));
            subject.setYear(rs.getInt("year"));
            subject.setSemester(rs.getInt("semester"));
            subject.setBranchId(rs.getInt("branch_id"));

            out.add(subject);
        }
    }

    private void fillSubject(ResultSet rs, List<Subject> out) throws SQLException {
        while (rs.next()) {
            Subject subject = new Subject();

            subject.setId(rs.getString("id"));
            subject.setName(rs.getString("name"));
            subject.setYear(rs.getInt("year"));
            subject.setSemester(rs.getInt("semester"));
            subject.setBranchId(rs.getInt("branch_id"));

            out.add(subject);
        }
    }
}
