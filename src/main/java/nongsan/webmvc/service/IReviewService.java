package nongsan.webmvc.service;

import java.util.List;

import nongsan.webmvc.model.Review;

public interface IReviewService {
    Boolean createReview(String reviewName, String reviewEmail, String reviewProductId, String reviewContent);

    Boolean updateReview(Review review);

    Boolean deleteReview(String id);

    Review findReviewById(Integer id);

    List<Review> findAllReview();

    List<Review> findReviewByProductId(String id);
}
