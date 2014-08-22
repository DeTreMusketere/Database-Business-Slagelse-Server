package model.data;

import abstracts.Data;
import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class Picture extends Data implements Serializable {

    private String name;
    private byte[] byteArray;

    public Picture(int id, String name, byte[] byteArray, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.byteArray = byteArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    @Override
    public String toString() {
        return "name: " + name;
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }

}
