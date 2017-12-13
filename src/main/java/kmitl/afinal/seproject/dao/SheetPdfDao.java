package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.SheetPdf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SheetPdfDao {

    private static SheetPdfDao instance;

    public static SheetPdfDao with(Connection connection) {
        if (instance == null) {
            instance = new SheetPdfDao();
        }
        instance.connection = connection;
        return instance;
    }

    private Connection connection;

    private SheetPdfDao() {

    }

    public List<SheetPdf> getAll() throws SQLException {
        String sql = "SELECT * FROM `sheet_pdf` ";

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<SheetPdf> list = new ArrayList<SheetPdf>();

        fillModel(rs, list);

        return list;
    }

    public SheetPdf getWithSheetId(int id) throws SQLException {
        String sql = "SELECT * FROM `sheet_pdf` WHERE sheet_id = ?";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<SheetPdf> list = new ArrayList<SheetPdf>();

        fillModel(rs, list);

        return list.get(0);
    }

    public int insert(SheetPdf sheetPdf) throws SQLException {
        String sql = "INSERT INTO `sheet_pdf` (`sheet_id`, `path`) " +
                "VALUES (?, ?)";

        PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, sheetPdf.getSheetId());
        stm.setString(2, sheetPdf.getPath());

        int affectedRows = stm.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating sheetPdf failed, no rows affected.");
        }

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating sheetPdf failed, no ID obtained.");
            }
        }
    }

    private void fillModel(ResultSet rs, List<SheetPdf> out) throws SQLException {
        while (rs.next()) {
            SheetPdf sheetPdf = new SheetPdf();

            sheetPdf.setId(rs.getInt("id"));
            sheetPdf.setSheetId(rs.getInt("sheet_id"));
            sheetPdf.setPath(rs.getString("path"));
            sheetPdf.setCreateTime(rs.getString("create_time"));
            sheetPdf.setUpdateTime(rs.getString("update_time"));

            out.add(sheetPdf);
        }
    }
}
