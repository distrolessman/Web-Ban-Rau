package nongsan.webmvc.dao.impl;

import java.util.List;

import nongsan.webmvc.dao.IReviewDAO;
import nongsan.webmvc.model.Review;
import org.hibernate.Session;
import javax.persistence.Query;

public class ReviewDAOImpl extends BaseDAO<Review> implements IReviewDAO {

    public ReviewDAOImpl() {
        super();
        setType(Review.class);
    }

    @Override
    public Boolean createReview(Review review) {
        return save(review);
    }

    @Override
    public Boolean updateReview(Review review) {
        return save(review);
    }

    @Override
    public Boolean deleteReview(Integer id) {
        return delete(id);
    }

    @Override
    public List<Review> getAllReview() {
        return findAll();
    }

    @Override
    public Review findReviewById(Integer id) {
        return findById(id);
    }

    public List<Review> findReviewByProductId(Integer productId) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Query query = session.createQuery("FROM Review R WHERE R.product.id=:id");
            query.setParameter("id", productId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }
}
