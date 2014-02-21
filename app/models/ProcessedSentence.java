package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

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

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setContent(String content) {
        this.content = content;
    }

}


