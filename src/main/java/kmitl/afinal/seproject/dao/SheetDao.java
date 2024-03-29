package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.Sheet;
import kmitl.afinal.seproject.model.SheetView;

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

    public Sheet getSheet(int id) throws SQLException {
        String sql = "SELECT * FROM `sheet` WHERE id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<Sheet> list = new ArrayList<Sheet>();

        fillModel(rs, list);

        return list.get(0);
    }

    public List<Sheet> getSheetBySubjectId(int subjectId) throws SQLException {
        String sql = "SELECT * FROM `sheet` WHERE subject_id = ? ORDER BY update_time DESC";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, subjectId);
        ResultSet rs = stm.executeQuery();

        List<Sheet> list = new ArrayList<Sheet>();

        fillModel(rs, list);

        return list;
    }

    public List<SheetView> getSheetView(int subjectId) throws SQLException {
        String sql = "SELECT d1.id, d2.id as pdf_id, d1.type, d1.title, d2.path as pdf_path, d1.create_by, d1.create_time, d1.update_time " +
                "FROM sedb.sheet AS d1 " +
                "INNER JOIN sedb.sheet_pdf AS d2 " +
                "ON  (d1.id = d2.sheet_id) " +
                "WHERE subject_id = ? " +
                "ORDER BY update_time DESC";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, subjectId);
        ResultSet rs = stm.executeQuery();

        List<SheetView> list = new ArrayList<SheetView>();

        while (rs.next()) {
            SheetView sheetView = new SheetView();

            sheetView.setId(rs.getInt("id"));
            sheetView.setPdfId(rs.getInt("pdf_id"));
            sheetView.setType(rs.getString("type"));
            sheetView.setTitle(rs.getString("title"));
            sheetView.setPdfPath(rs.getString("pdf_path"));
            sheetView.setCreate_by(rs.getString("create_by"));
            sheetView.setCreateTime(rs.getString("create_time"));
            sheetView.setUpdateTime(rs.getString("update_time"));

            list.add(sheetView);
        }

        return list;
    }

    public int insert(Sheet sheet) throws SQLException {
        String sql = "INSERT INTO `sheet` (`type`, `title`, `term`, `subject_id`, `branch_id`, `department_id`, `faculty_id`, `create_by`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, sheet.getType());
        stm.setString(2, sheet.getTitle());

        if (sheet.getTerm() == null) {
            stm.setNull(3, Types.INTEGER);
        } else {
            stm.setInt(3, sheet.getTerm());
        }

        if (sheet.getSubject_id() == null) {
            stm.setNull(4, Types.VARCHAR);
        } else {
            stm.setString(4, sheet.getSubject_id());
        }

        if (sheet.getBranch_id() == null) {
            stm.setNull(5, Types.INTEGER);
        } else {
            stm.setInt(5, sheet.getBranch_id());
        }

        if (sheet.getDepartment_id() == null) {
            stm.setNull(6, Types.INTEGER);
        } else {
            stm.setInt(6, sheet.getDepartment_id());
        }

        if (sheet.getFaculty_id() == null) {
            stm.setNull(7, Types.INTEGER);
        } else {
            stm.setInt(7, sheet.getFaculty_id());
        }

        stm.setString(8, sheet.getCreate_by());

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

    public void delete(int sheetId) throws SQLException {
        String sql = "DELETE FROM `sheet` WHERE id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, sheetId);

        stm.execute();
    }

    public List<Sheet> search(Sheet info) throws SQLException {
        String sql = "SELECT * FROM `sheet` WHERE 1=1 ";

        if (info.getTitle() != null) {
            String[] keywords = info.getTitle().split(" ");
            sql += keywords.length > 1 ?
                    " AND MATCH (title) AGAINST ('" + info.getTitle() + "' IN NATURAL LANGUAGE MODE)" :
                    " AND title LIKE '%" + info.getTitle() + "%' ";
        }

        sql += info.getFaculty_id() != null ? " AND faculty_id=" + info.getFaculty_id() : "";
        sql += info.getDepartment_id() != null ? " AND department_id=" + info.getDepartment_id() : "";
        sql += info.getBranch_id() != null ? " AND branch_id=" + info.getBranch_id() : "";
        sql += info.getSubject_id() != null ? " AND subject_id=" + info.getSubject_id() : "";

        sql += " ORDER BY `create_time` DESC";

        PreparedStatement stm = connection.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();

        List<Sheet> list = new ArrayList<>();

        fillModel(rs, list);

        return list;
    }

    private void fillModel(ResultSet rs, List<Sheet> list) throws SQLException {
        while (rs.next()) {
            Sheet sheet = new Sheet();

            sheet.setId(rs.getInt("id"));
            sheet.setType(rs.getString("type"));
            sheet.setTitle(rs.getString("title"));
            sheet.setTerm(rs.getInt("term"));
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
