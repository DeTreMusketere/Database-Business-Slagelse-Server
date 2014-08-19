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
    public void insert(Picture source) {
        try {
            Statement st = DBTool.getStatement();

            int id = source.getId();
            String name = source.getName();
            byte[] byteArray = source.getByteArray();
            int updateNumber = source.getUpdateNumber();

            String sql = "INSERT INTO picture (id_picture,name, update_number) VALUES(" + id + ",'" + name + "', '" + updateNumber + "');";
            st.execute(sql);

            fileHandler.saveByteArray(byteArray, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Picture source, Picture target) {
        try {
            Statement st = DBTool.getStatement();

            int targetId = target.getId();
            String name = source.getName();
            byte[] byteArray = source.getByteArray();
            fileHandler.saveByteArray(byteArray, targetId);
            int updateNumber = source.getUpdateNumber();

            String sql = "UPDATE picture SET name='" + name + "', update_number='" + updateNumber + "' WHERE id_picture=" + targetId + ";";
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
                    int updateNumber = rs.getInt("update_number");

                    Picture p = new Picture(id, name, byteArray, updateNumber);
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
                    int updateNumber = rs.getInt("update_number");
                    Picture p = new Picture(id, name, byteArray, updateNumber);
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
