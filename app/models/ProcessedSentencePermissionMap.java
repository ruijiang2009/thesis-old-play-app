package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 4/13/14
 * Time: 5:24 PM
 */

@Entity
@Table(name = "pocessed_sentence_permission_map")
public class ProcessedSentencePermissionMap implements Serializable{
    private static final long serialVersionUID = 4264546498700495063L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission", nullable = false)
    private Permission permission;

    @Id
    @Column(name = "permission")
    private Long permission;

    @Id
    @Column(name = "processed_sentence")
    private Long processedSentence;

    public voilive


}
