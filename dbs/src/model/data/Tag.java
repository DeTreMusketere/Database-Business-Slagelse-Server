
package model.data;

import abstracts.Data;

/**
 *
 * @author Patrick
 */
public class Tag extends Data {
    
    private String name;
    private String description;

    public Tag(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

}
