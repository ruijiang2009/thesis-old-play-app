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
@Table(name = "app")
public class App {

    private static final long serialVersionUID = 4264546498700495061L;

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

    public static App findById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(App.class, id);

        // return JPA.em().find(App.class, id); this is not working
    }

    public static List<App> findByCategoryId(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = JPA.em().createNativeQuery(
//        Query query = entityManager.createNativeQuery(
                String.format("SELECT  a.id, a.name, a.description FROM app a JOIN app_category_map acm ON a.id = acm.app WHERE acm.category='%d';", id),
                App.class);
        return query.getResultList();
    }

    public static List<App> findByPermissionId(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery(
                String.format("select a.id, a.name, a.description " +
                        "from app a " +
                        "join app_permission_map apm on a.id = apm.app " +
                        "join permission p on apm.permission = p.id where p.id = %d;", id),
                App.class);
        System.out.println(String.format("select a.id, a.name " +
                "from app a " +
                "join app_permission_map apm on a.id = apm.app " +
                "join permission p on apm.permission = p.id where p.id = %d;", id));
        return query.getResultList();
    }

}
