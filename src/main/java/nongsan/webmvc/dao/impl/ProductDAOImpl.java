package nongsan.webmvc.dao.impl;

import java.util.List;


import nongsan.webmvc.model.Product;

import nongsan.webmvc.dao.IProductDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductDAOImpl extends BaseDAO<Product> implements IProductDAO {

    public ProductDAOImpl() {
        super();
        setType(Product.class);
    }

    @Override
    public Boolean createProduct(Product product) {
        return save(product);
    }

    @Override
    public Boolean updateProduct(Product product) {
        return save(product);
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        return delete(id);
    }

    @Override
    public Product findProductById(Integer id) {
        return findById(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return findAll();
    }

    @Override
    public List<Product> getProductByCategoryId(Integer id) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Query query = session.createQuery("FROM Product P WHERE P.catalog.id=:id");
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

    @Override
    public List<Product> findProductByName(String keyword) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Query query = session.createQuery("FROM Product WHERE name LIKE :keyword");
            query.setParameter("keyword", "%" + keyword + "%");
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
