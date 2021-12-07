package nongsan.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Catalog parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalog")
    private List<Product> products;

    @Override
    public String toString() {
        return "Catalog [id=" + id + ", name=" + name + ", parent_id=" + parent.getId() + "]";
    }
}
