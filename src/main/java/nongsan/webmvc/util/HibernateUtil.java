package nongsan.webmvc.util;

/*import com.example.projectshoes.model.AbstractModel;
import com.example.projectshoes.model.CategoryModel;
import com.example.projectshoes.model.CustomerModel;
import com.example.projectshoes.model.DeliveryModel;
import com.example.projectshoes.model.ProductModel;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.model.SaledetailModel;
import com.example.projectshoes.model.StockModel;
import com.example.projectshoes.model.UserModel;*/

import java.util.Properties;

import nongsan.webmvc.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static final SessionFactory FACTORY;

    static {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, System.getenv("DB_CONNECTION_STRING"));
        settings.put(Environment.USER, System.getenv("DB_USERNAME"));
        settings.put(Environment.PASS, System.getenv("DB_PASSWORD"));
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.POOL_SIZE, "100");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(BoardNew.class);
        configuration.addAnnotatedClass(Catalog.class);
        configuration.addAnnotatedClass(Ordered.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Review.class);
        configuration.addAnnotatedClass(Transaction.class);
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        FACTORY = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}