
package model.data;

import abstracts.Data;

/**
 *
 * @author Patrick
 */
public class Picture extends Data {
    
    private String name;
    private String url;

    public Picture(int id, String name, String url) {
        super(id);
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "name: " + name + " url: " + url;
    }

}
