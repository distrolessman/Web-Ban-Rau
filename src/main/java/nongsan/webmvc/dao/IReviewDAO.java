package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.Review;

public interface IReviewDAO extends IBaseDAO<Review> {
    Boolean createReview(Review review);

    Boolean updateReview(Review review);

    Boolean deleteReview(Integer id);

    Review findReviewById(Integer id);

    List<Review> getAllReview();

    List<Review> findReviewByProductId(Integer productId);
}
