package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.BaseModel;
import kmitl.afinal.seproject.model.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDao {

    public static BranchDao instance;

    public static BranchDao with(Connection connection) {
        if (instance == null) {
            instance = new BranchDao();
        }
        instance.connection = connection;
        return instance;
    }

    private Connection connection;

    private BranchDao() {

    }

    public List<BaseModel> getAll() throws SQLException {
        String sql = "SELECT * FROM `branch` ";

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    public List<BaseModel> getWithDepartmentId(int id) throws SQLException {
        String sql = "SELECT * FROM `branch` WHERE department_id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<BaseModel> list = new ArrayList<BaseModel>();

        fillModel(rs, list);

        return list;
    }

    public int getMaxYear(int id) throws SQLException {
        String sql = "SELECT `max_year` FROM `branch` WHERE id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return rs.getInt("max_year");
        }
        return 0;
    }

    private void fillModel(ResultSet rs, List<BaseModel> out) throws SQLException {
        while (rs.next()) {
            Branch branch = new Branch();

            branch.setId(rs.getInt("id"));
            branch.setName(rs.getString("name"));
            branch.setMaxYear(rs.getInt("max_year"));
            branch.setDepartmentId(rs.getInt("department_id"));

            out.add(branch);
        }
    }
}
