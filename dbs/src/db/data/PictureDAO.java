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

    /**
     * Saves a picture's id, name and update number in the database. Saves the
     * bytearray in a local file.
     *
     * @param source The picture to insert.
     */
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

            fileHandler.savePictureByteArray(byteArray, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates a picture in the database and the locally saved bytearray.
     *
     * @param source The picture to overwrite the target.
     * @param target The picture to be changed.
     */
    @Override
    public void update(Picture source, Picture target) {
        try {
            Statement st = DBTool.getStatement();

            int targetId = target.getId();
            String name = source.getName();
            byte[] byteArray = source.getByteArray();
            fileHandler.savePictureByteArray(byteArray, targetId);
            int updateNumber = source.getUpdateNumber();

            String sql = "UPDATE picture SET name='" + name + "', update_number='" + updateNumber + "' WHERE id_picture=" + targetId + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes a picture in the database and deletes the corresponding local
     * file.
     *
     * @param target Picture to delete.
     */
    @Override
    public void delete(Picture target) {
        try {
            Statement st = DBTool.getStatement();

            int targetId = target.getId();

            fileHandler.deletePictureFile(targetId);

            String sql = "DELETE FROM picture WHERE id_picture=" + targetId + ";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets a picture from the database and attaches the bytearray from a local
     * file.
     *
     * @param id The id of the picture to get.
     * @return Returns the picture with the given id.
     */
    @Override
    public Picture select(int id) {
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM picture WHERE id_picture=" + id + ";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String name = rs.getString("name");

                    byte[] byteArray = fileHandler.getPictureByteArray(id);
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

    /**
     * Gets all pictures from the database including their bytearrays from local
     * files
     *
     * @return ArrayList<Picture> containing all existing pictures.
     */
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

                    byte[] byteArray = fileHandler.getPictureByteArray(id);
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
