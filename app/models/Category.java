package models;

import javax.persistence.*;

import play.db.jpa.JPA;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/6/14
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "category")
public class Category {


    @Id
    @GeneratedValue(generator="increment")
    private Long id;

    @Column(length = 45)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Category findById(Long id) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.find(Category.class, id);

        return JPA.em().find(Category.class, id);
    }


}
