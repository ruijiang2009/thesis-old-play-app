package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/13/14
 * Time: 8:45 PM
 */

@Entity
@Table(name = "sentence")
public class Sentence implements Serializable{
    private static final long serialVersionUID = 4264546498700495061L;

    @OneToMany(mappedBy = "sentence")
//    @JoinColumn(name ="id", nullable = true)
    private Set<ProcessedSentence> processedSentences= new HashSet<ProcessedSentence>();

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "id")
    private Long id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "app")
    private Long app;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    public Set<ProcessedSentence> getProcessedSentences() {
        return this.processedSentences;
    }

    public void setProcessedSentences(Set<ProcessedSentence> processedSentences) {
        this.processedSentences = processedSentences;
    }

    public Sentence() {

    }

    public Sentence(Long id, String content, Long app) {
        this.id = id;
        this.content = content;
        this.app = app;
    }

    public Sentence(String content, Long app ) {
        this.content = content;
        this.app = app;
        this.id = null;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public Long getApp() {
        return app;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setApp(Long app) {
        this.app = app;
    }

    public void save() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<Sentence> findByApp(Long id) {
        Query query = JPA.em().createNativeQuery(String.format("SELECT id, content, app FROM sentence WHERE app = %d", id), Sentence.class);
        return query.getResultList();
    }

    public static Sentence findById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Sentence.class, id);
    }
}
