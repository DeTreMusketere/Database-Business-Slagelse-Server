package db.data;

import abstracts.DataDAO;
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

    @Override
    public int insert(Picture source) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            String url = source.getUrl();

            String sql = "INSERT INTO picture (name, url) VALUES('" + name + "', '" + url + "');";
            st.execute(sql);

            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();
            int id = rs.getInt(1);

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
            String url = source.getUrl();
            
            String sql = "UPDATE picture SET name='"+name+"', url='"+url+"' WHERE id_picture="+targetId+";";
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
            
            String sql = "DELETE FROM picture WHERE id_picture="+targetId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Picture select(int id) {
        try {
            Statement st = DBTool.getStatement();
            
            
            
            String sql = "SELECT * FROM picture WHERE id_picture="+id+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    String name = rs.getString("name");
                    String url = rs.getString("url");
                    Picture p = new Picture(id, name, url);
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
                while(rs.next()) {
                    int id = rs.getInt("id_picture");
                    String name = rs.getString("name");
                    String url = rs.getString("url");
                    Picture p = new Picture(id, name, url);
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
