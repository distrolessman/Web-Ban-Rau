package nongsan.webmvc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nongsan.webmvc.dao.IProductDAO;
import nongsan.webmvc.dao.IReviewDAO;
import nongsan.webmvc.dao.impl.ProductDAOImpl;
import nongsan.webmvc.dao.impl.ReviewDAOImpl;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.model.Review;
import nongsan.webmvc.service.IReviewService;
import nongsan.webmvc.util.DateConvertUtil;

import javax.inject.Inject;

public class ReviewServicesImpl implements IReviewService {
    @Inject
    IReviewDAO reviewDAO;
    @Inject
    IProductDAO productDAO;

    @Override
    public Boolean createReview(String reviewName, String reviewEmail, String reviewProductId,
                                String reviewContent) {
        try {
            Review review = new Review();
            Product productReviewed = productDAO.findProductById(Integer.parseInt(reviewProductId));
            review.setName(reviewName);
            review.setEmail(reviewEmail);
            review.setProduct(productReviewed);
            review.setContent(reviewContent);
            review.setCreated(new Date(System.currentTimeMillis()));
            return reviewDAO.createReview(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateReview(Review review) {
        return reviewDAO.updateReview(review);
    }

    @Override
    public Boolean deleteReview(String id) {
        try {
            return reviewDAO.deleteReview(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Review findReviewById(Integer id) {
        return reviewDAO.findReviewById(id);
    }

    @Override
    public List<Review> findAllReview() {
        return reviewDAO.getAllReview();
    }

    @Override
    public List<Review> findReviewByProductId(String productId) {
        try {
            return reviewDAO.findReviewByProductId(Integer.parseInt(productId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
