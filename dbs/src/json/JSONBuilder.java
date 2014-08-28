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

    public JSONArray buildJSONArray(int updateNumber) {
        System.out.println("BUILDING JSON SHIT");
        JSONArray all = new JSONArray();
        JSONArray sales = new JSONArray();
        JSONArray pictures = new JSONArray();

        for (Sale s : saleRegister.getObjects()) {
            if (s.getUpdateNumber() > updateNumber) {
                sales.put(s.toJSONObject());
                System.out.println("MADE A SALE JSON");
            }
        }

        for (Picture p : pictureRegister.getObjects()) {
            if (p.getUpdateNumber() > updateNumber) {
                pictures.put(p.toJSONObject());
                
            }
        }

        System.out.println("SENDING THEM NOW");
        all.put(sales);
        all.put(pictures);
        return all;
    }

}
