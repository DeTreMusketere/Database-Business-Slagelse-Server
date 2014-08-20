package model.data;

import abstracts.Data;

/**
 *
 * @author Patrick
 */
public class Tag extends Data {

    private String name;
    private String description;
    private int updateNumber;

    public Tag(int id, String name, String description, int updateNumber) {
        super(id);
        this.name = name;
        this.description = description;
        this.updateNumber = updateNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public void setUpdateNumber(int updateNumber) {
        this.updateNumber = updateNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "name: " + name + " description: " + description;
    }

}
