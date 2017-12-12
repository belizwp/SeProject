package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.BaseModel;
import kmitl.afinal.seproject.model.Subject;

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
}
