package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.Sheet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SheetDao {
    public static SheetDao instance;

    public static SheetDao with(Connection connection) {
        if (instance == null) {
            instance = new SheetDao();
        }
        instance.connection = connection;
        return instance;
    }

    private Connection connection;

    private SheetDao() {

    }

    public List<Sheet> getAll() throws SQLException {
        String sql = "SELECT * FROM `sheet`";

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<Sheet> list = new ArrayList<Sheet>();

        fillModel(rs, list);

        return list;
    }

    public List<Sheet> getSheet(String user) throws SQLException {
        String sql = "SELECT * FROM `sheet` WHERE create_by = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, user);
        ResultSet rs = stm.executeQuery();

        List<Sheet> list = new ArrayList<Sheet>();

        fillModel(rs, list);

        return list;
    }

    private void fillModel(ResultSet rs, List<Sheet> list) throws SQLException {
        while (rs.next()) {
            Sheet sheet = new Sheet();

            sheet.setId(rs.getInt("id"));
            sheet.setType(rs.getString("type"));
            sheet.setTitle(rs.getString("title"));
            sheet.setSubject_id(rs.getString("subject_id"));
            sheet.setBranch_id(rs.getInt("branch_id"));
            sheet.setDepartment_id(rs.getInt("department_id"));
            sheet.setFaculty_id(rs.getInt("faculty_id"));
            sheet.setCreate_by(rs.getString("create_by"));
            sheet.setCreateTime(rs.getString("create_time"));
            sheet.setUpdateTime(rs.getString("update_time"));

            list.add(sheet);
        }
    }


}
