package models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/15/14
 * Time: 10:23 PM
 */
public class PermissionRank implements Serializable {
    public Long id;
    public String name;
    public Double rate;

    public PermissionRank(Long id, String name, Double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public String toString() {
        return String.format("id: %d name: %s rate: %f", id, name, rate);
    }
}
