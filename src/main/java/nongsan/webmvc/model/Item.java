package nongsan.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Product product;
    private Integer qty;
    private double price;

    @Override
    public String toString() {
        return "Item [id=" + id + ", product=" + product + ", qty=" + qty + ", price=" + price + "]";
    }
}
