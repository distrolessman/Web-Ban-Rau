package nongsan.webmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Date created;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, String phone, String email, String name, Date created) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.created = created;
    }

    public User(String username, String password, String email, String phone, String name, Date created) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.created = created;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", username=" + username
                + ", password=" + password + ", created=" + created + "]";
    }
}
