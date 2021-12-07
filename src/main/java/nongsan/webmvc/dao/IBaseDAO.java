package nongsan.webmvc.dao;
import java.util.List;

public interface IBaseDAO<T> {
    List<T> findAll();
    T findById(Integer id);
    Boolean save(T object);
    Boolean delete(Integer id);
    Long count(String sql, Object... parameters);
    List<T> queryHibernate(String sql, T object);
}
