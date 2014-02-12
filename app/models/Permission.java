package models;

import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.List;

/**
 * User: ruijiang
 * Date: 2/11/14
 * Time: 9:55 PM
 */
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Permission findById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Permission.class, id);

        // return JPA.em().find(App.class, id); this is not working
    }

    public static List<Permission> findByAppId(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //Query query = JPA.em().createNativeQuery(
        Query query = entityManager.createNativeQuery(
                String.format("select p.id, p.name, p.description from app a " +
                        "join app_permission_map apm on a.id = apm.app " +
                        "join permission p on apm.permission = p.id where a.id = %d;", id),
                Permission.class);
        return query.getResultList();
    }

}
