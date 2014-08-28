package json;

import model.data.Picture;
import model.data.PictureRegister;
import model.data.Sale;
import model.data.SaleRegister;
import org.json.JSONArray;

/**
 *
 * @author Patrick
 */
public class JSONBuilder {

    private SaleRegister saleRegister;
    private PictureRegister pictureRegister;

    public JSONBuilder(SaleRegister saleRegister, PictureRegister pictureRegister) {
        this.saleRegister = saleRegister;
        this.pictureRegister = pictureRegister;
    }

    /**
     * Builds a JSONArray containing two JSONArrays. One containing all sales
     * created or updated with a higher update number than the one given as a
     * parameter. One containing all pictures created or updated with a higher
     * update number than the one given as a parameter
     *
     * @param updateNumber
     * @return JSONArray containing a JSONArray of new sales and a JSONArray pf
     * new pictures.
     */
    public JSONArray buildJSONArray(int updateNumber) {
        JSONArray all = new JSONArray();
        JSONArray sales = new JSONArray();
        JSONArray pictures = new JSONArray();

        for (Sale s : saleRegister.getObjects()) {
            if (s.getUpdateNumber() > updateNumber) {
                sales.put(s.toJSONObject());
            }
        }

        for (Picture p : pictureRegister.getObjects()) {
            if (p.getUpdateNumber() > updateNumber) {
                pictures.put(p.toJSONObject());

            }
        }

        all.put(sales);
        all.put(pictures);
        return all;
    }

}
