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
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private String user_session;
    private String user_name;
    private String user_mail;
    private String user_phone;
    private String address;
    private String amount;
    private String payment;
    private String message;
    private String created;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction")
    List<Ordered> ordereds;
    @Override
    public String toString() {
        return "Transaction [id=" + id + ", status=" + status + ", user_session=" + user_session + ", user_name="
                + user_name + ", user_mail=" + user_mail + ", user_phone=" + user_phone + ", address=" + address
                + ", amount=" + amount + ", payment=" + payment + ", message=" + message + ", created=" + created + "]";
    }
}
