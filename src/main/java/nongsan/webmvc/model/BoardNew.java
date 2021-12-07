package nongsan.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boardnew")
public class BoardNew implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String image_link;
    private String author;
    private Date created;

    @Override
    public String toString() {
        return "BoardNew [id=" + id + ", title=" + title + ", content=" + content + ", image_link=" + image_link
                + ", author=" + author + ", created=" + created + "]";
    }
}
