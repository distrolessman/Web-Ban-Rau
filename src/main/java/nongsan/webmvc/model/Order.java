package nongsan.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer id;
    private List<Item> items;
    private double sumPrice;
    private String created;

    @Override
    public String toString() {
        return "Order [id=" + id + ", items=" + items + ", sumPrice=" + sumPrice + ", created=" + created + "]";
    }
}
