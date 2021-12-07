package nongsan.webmvc.dao.impl;

import java.util.List;

import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.dao.ICategoryDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CategoryDAOImpl extends BaseDAO<Catalog> implements ICategoryDAO {

    public CategoryDAOImpl() {
        super();
        setType(Catalog.class);
    }

    @Override
    public Boolean createCategory(Catalog category) {
        return save(category);
    }

    @Override
    public Boolean updateCategory(Catalog category) {
        return save(category);
    }

    @Override
    public Catalog findCategoryById(Integer id) {
        return findById(id);
    }

    @Override
    public List<Catalog> findAllCategory() {
        return findAll();
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        return delete(id);
    }

    @Override
    public List<Catalog> findCategoryByProduct(Integer id) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Query query = session.createQuery("FROM Catalog C INNER JOIN Product P ON C.id = P.catalog.id WHERE P.id = :id");
            query.setParameter("id", id);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }
}
