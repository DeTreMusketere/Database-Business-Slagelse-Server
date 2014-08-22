package abstracts;

import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public abstract class Data {

    private int id;
    protected int updateNumber;

    public Data(int id, int updateNumber) {
        this.id = id;
        this.updateNumber = updateNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public void setUpdateNumber(int updateNumber) {
        this.updateNumber = updateNumber;
    }
    
    public abstract JSONObject toJSONObject();

}
