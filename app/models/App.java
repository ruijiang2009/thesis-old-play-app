package models;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/10/14
 * Time: 9:57 PM
 */
@Entity
@Table(name = "app")
public class App implements Serializable{

    private static final long serialVersionUID = 4264546498700495061L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @ManyToMany(mappedBy = "app_permission_map")
//    private Set<Permission> permissions = new HashSet<Permission>();

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

    public static List<App> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("SELECT id, NAME, description FROM app ORDER BY id ASC", App.class);
        return query.getResultList();
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

    public List<String> processDescription() {
        String paragraph = this.description;
        Reader reader = new StringReader(paragraph);
        DocumentPreprocessor dp = new DocumentPreprocessor(reader);

        List<String> sentenceList = new LinkedList<String>();
        Iterator<List<HasWord>> it = dp.iterator();
        while (it.hasNext()) {
            StringBuilder sentenceSb = new StringBuilder();
            List<HasWord> sentence = it.next();
            for (HasWord token : sentence) {
                if(sentenceSb.length()>1) {
                    sentenceSb.append(" ");
                }
                sentenceSb.append(token);
            }
            sentenceList.add(sentenceSb.toString());
        }
        System.out.println(String.format("sentence list is %d", sentenceList.size()));

        for(String sentence:sentenceList) {
            System.out.println(sentence);
        }
        return sentenceList;
    }





}
