package models;

import play.db.jpa.JPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/10/14
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class App {

    private static final long serialVersionUID = 4264546498700495061L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static App findById(Long id) {
        return JPA.em().find(App.class, id);
    }

    public static List<App> findByCategoryId(Long id) {
        Query query = JPA.em().createNativeQuery(
                String.format("SELECT  a.id, a.name FROM app a JOIN app_category_map acm ON a.id = acm.app WHERE acm.category=%d", id),
                App.class);
        return query.getResultList();
    }



}
