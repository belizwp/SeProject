package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.Sheet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SheetDao {

    private static SheetDao instance;

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

    public int insert(Sheet sheet) throws SQLException {
        String sql = "INSERT INTO `sheet` (`type`, `title`, `subject_id`, `branch_id`, `department_id`, `faculty_id`, `create_by`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, sheet.getType());
        stm.setString(2, sheet.getTitle());

        if (sheet.getSubject_id() == null) {
            stm.setNull(3, Types.VARCHAR);
        } else {
            stm.setString(3, sheet.getSubject_id());
        }

        if (sheet.getBranch_id() == null) {
            stm.setNull(4, Types.INTEGER);
        } else {
            stm.setInt(4, sheet.getBranch_id());
        }

        if (sheet.getDepartment_id() == null) {
            stm.setNull(5, Types.INTEGER);
        } else {
            stm.setInt(5, sheet.getDepartment_id());
        }

        if (sheet.getFaculty_id() == null) {
            stm.setNull(6, Types.INTEGER);
        } else {
            stm.setInt(6, sheet.getFaculty_id());
        }

        stm.setString(7, sheet.getCreate_by());

        int affectedRows = stm.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating sheet failed, no rows affected.");
        }

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating sheet failed, no ID obtained.");
            }
        }
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
