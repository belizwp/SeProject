package kmitl.afinal.seproject.dao;

import kmitl.afinal.seproject.model.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDao {

    private Connection connection;

    public FacultyDao(Connection connection) {
        this.connection = connection;
    }

    public List<Faculty> getAll() throws SQLException {
        String sql = "SELECT * FROM `faculty` ";

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        List<Faculty> list = new ArrayList<Faculty>();

        while (rs.next()) {
            Faculty faculty = new Faculty();

            faculty.setId(rs.getInt("id"));
            faculty.setName(rs.getString("name"));

            list.add(faculty);
        }

        return list;
    }
}
