package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.BaseModel;
import kmitl.afinal.seproject.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    public static DepartmentDao instance;

    public static DepartmentDao with(Connection connection) {
        if (instance == null) {
            instance = new DepartmentDao();
        }
        instance.connection = connection;
        return instance;
    }

    private Connection connection;

    private DepartmentDao() {

    }

    public List<BaseModel> getAll() throws SQLException {
        String sql = "SELECT * FROM `department` ";

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    public List<BaseModel> getWithFacultyId(int id) throws SQLException {
        String sql = "SELECT * FROM `department` WHERE faculty_id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    private void fillModel(ResultSet rs, List<BaseModel> out) throws SQLException {
        while (rs.next()) {
            Department department = new Department();

            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            department.setFacultyId(rs.getInt("faculty_id"));

            out.add(department);
        }
    }
}
