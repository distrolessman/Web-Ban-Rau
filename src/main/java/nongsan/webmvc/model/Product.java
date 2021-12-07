package nongsan.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;
    private String name;
    private Integer price;
    private Integer status;
    private String description;
    private String content;
    private String image_link;
    private Date created;
    private Integer discount;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<Ordered> ordereds;

    @Override
    public String toString() {
        return "Product [id=" + id + ", catalog_id=" + catalog.getId() + ", name=" + name + ", price=" + price + ", status="
                + status + ", description=" + description + ", content=" + content + ", discount=" + discount
                + ", image_link=" + image_link + ", created="
                + created.toString() + "]";
    }
}
