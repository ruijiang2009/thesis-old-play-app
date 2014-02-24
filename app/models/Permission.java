package models;

import org.hibernate.Session;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: ruijiang
 * Date: 2/11/14
 * Time: 9:55 PM
 */
@Entity
@Table(name = "permission")
public class Permission  implements Serializable{

    @OneToMany(mappedBy = "permission")
    private Set<AppPermissionMap> appPermissionMaps = new HashSet<AppPermissionMap>();

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;



//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "app_permission_map",
//               joinColumns = {@JoinColumn(name="id")},
//               inverseJoinColumns = {@JoinColumn(name="app")})
//    private Set<App> apps = new HashSet<App>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
    public Set<AppPermissionMap> getAppPermissionMaps() {
        return this.appPermissionMaps;
    }

    public void setAppPermissionMaps(Set appPermissionMaps) {
        this.appPermissionMaps = appPermissionMaps;
    }

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
    }

    public static List<Permission> findByAppId(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery(
                String.format("select p.id, p.name, p.description from app a " +
                        "join app_permission_map apm on a.id = apm.app " +
                        "join permission p on apm.permission = p.id where a.id = %d;", id),
                Permission.class);
        List<Permission> result = query.getResultList();
        entityManager.close();
        return result;
    }

//    public static List findByRank() {
//        EntityManager entityManager = JPA.em();
////        Session session = (Session)entityManager.getDelegate();
////        org.hibernate.Query query = session.createQuery("SELECT p.id, p.name, COUNT(apm.app) as rate FROM app_permission_map apm JOIN permission p WHERE apm.permission = p.id GROUP BY permission ORDER BY rate DESC");
////        return query.list();org.hibernate.hql.internal.ast.QuerySyntaxException: app_permission_map is not mapped
//        TypedQuery query = entityManager.createQuery("SELECT new PermissionRank(id, name, COUNT(app) AS rate) FROM AppPermissionMap RIGHT JOIN AppPermissionMap.permission Permission GROUP BY permission ORDER BY rate DESC",
//                PermissionRank.class);
//        return query.getResultList();
//
//    }

    @Transactional
    public static List<Permission> findAll() {
        EntityManager entityManager = JPA.em();
        Query query = entityManager.createNativeQuery("SELECT id, name, description FROM permission ORDER BY id ASC", Permission.class);
        List<Permission> result = query.getResultList();
        entityManager.close();
        return result;
    }
}
