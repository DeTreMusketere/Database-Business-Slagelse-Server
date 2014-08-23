package model.data;

import abstracts.Data;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
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
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", name);
        String base64String = Base64.encode(byteArray);
        obj.put("array", base64String);
        obj.put("updatenumber", getUpdateNumber());
        return obj;
    }

}
