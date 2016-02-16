package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/17/14
 * Time: 6:12 PM
 */
@Entity
@Table(name = "processed_sentence")
public class ProcessedSentence implements  Serializable{
    private static final long serialVersionUID = 4264546498700495061L;

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentence", nullable = false)
    private Sentence sentence;

//    @ManyToMany
//    @JoinTable(name="processed_sentence_permission_map",
//        joinColumns={@JoinColumn(name="processed_sentence", referencedColumnName="ID")})
//    private List<Permission> permissionList;


    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProcessedSentence(Long id, String content, Sentence sentence) {
        this.id = id;
        this.content = content;
        this.sentence = sentence;
    }

    public ProcessedSentence(String content, Sentence sentence) {
        this(null, content, sentence);
    }

    public ProcessedSentence() {
        this(null, null, null);
    }

    public static ProcessedSentence findById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProcessedSentence processedSentence = entityManager.find(ProcessedSentence.class, id);
        entityManager.close();
        return processedSentence;
    }
}


