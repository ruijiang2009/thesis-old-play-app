package models;

import javax.persistence.*;
import play.db.jpa.Transactional;

import play.db.jpa.JPA;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/6/14
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */

// hibernate 4.2.8
@Entity
@Table(name = "category")
//@Entity(name="category") hibernate 4.3.0
public class Category implements Serializable {

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

    public static Category findById(Long id) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.find(Category.class, id);

        return JPA.em().find(Category.class, id);
    }

    @play.db.jpa.Transactional
    public static List<Category> findAll() {
        EntityManager entityManager = JPA.em();
        Query query = JPA.em().createNativeQuery("SELECT category.id, category.name FROM category", Category.class);
        List<Category> categories =  query.getResultList();
        return categories;
    }

}
