package db.data;

import abstracts.DataDAO;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.Tag;

/**
 *
 * @author Patrick
 */
public class TagDAO extends DataDAO<Tag> {

    @Override
    public void insert(Tag source) {
        try {
            Statement st = DBTool.getStatement();

            int id = source.getId();
            String name = source.getName();
            String description = source.getDescription();
            int updateNumber = source.getUpdateNumber();
            String sql = "INSERT INTO tag (id_tag, name, description, update_number) VALUES(" + id + ",'" + name + "', '" + description + "', " + updateNumber + ");";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Tag source, Tag target) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            int updateNumber = source.getUpdateNumber();
            
            String sql = "UPDATE tag SET name='" + name + "', description='" + description + "', update_number=" + updateNumber + " WHERE id_tag=" + target.getId() + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Tag target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetid = target.getId();

            String sql = "DELETE FROM tag WHERE id_tag =" + targetid;
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Tag select(int id) {
        try {
            Statement st = DBTool.getStatement();
            Tag tag = null;
            String sql = "SELECT * FROM tag WHERE id_tag=" + id + ";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    tag = new Tag(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                }
            }
            return tag;
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Tag> selectAll() {
        ArrayList<Tag> tags = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM tag;";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    Tag tag = new Tag(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    tags.add(tag);
                }
            }

            return tags;
        } catch (SQLException ex) {
            Logger.getLogger(TagDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
