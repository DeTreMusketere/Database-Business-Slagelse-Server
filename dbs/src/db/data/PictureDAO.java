package db.data;

import abstracts.DataDAO;
import control.FileHandler;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Picture;

/**
 *
 * @author Patrick
 */
public class PictureDAO extends DataDAO<Picture> {

    private FileHandler fileHandler;

    public PictureDAO(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public int insert(Picture source) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            byte[] byteArray = source.getByteArray();

            String sql = "INSERT INTO picture (name) VALUES('" + name + "');";
            st.execute(sql);

            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();
            int id = rs.getInt(1);
            fileHandler.saveByteArray(byteArray, id);

            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public void update(Picture source, Picture target) {
        try {
            Statement st = DBTool.getStatement();

            int targetId = target.getId();
            String name = source.getName();
            byte[] byteArray = source.getByteArray();
            fileHandler.saveByteArray(byteArray, targetId);

            String sql = "UPDATE picture SET name='" + name + "' WHERE id_picture=" + targetId + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Picture target) {
        try {
            Statement st = DBTool.getStatement();

            int targetId = target.getId();

            fileHandler.deleteFile(targetId);

            String sql = "DELETE FROM picture WHERE id_picture=" + targetId + ";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Picture select(int id) {
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM picture WHERE id_picture=" + id + ";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String name = rs.getString("name");

                    byte[] byteArray = fileHandler.getByteArray(id);

                    Picture p = new Picture(id, name, byteArray);
                    return p;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Picture> selectAll() {
        ArrayList<Picture> pictures = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM picture;";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt("id_picture");
                    String name = rs.getString("name");

                    byte[] byteArray = fileHandler.getByteArray(id);
                    Picture p = new Picture(id, name, byteArray);
                    pictures.add(p);
                }
            }
            return pictures;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
