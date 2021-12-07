package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Review;
import nongsan.webmvc.service.IReviewService;
import nongsan.webmvc.service.impl.ReviewServicesImpl;

public class ReviewListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Inject
	IReviewService reviewService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Review> reviewList = reviewService.findAllReview();
		req.setAttribute("reviewlist", reviewList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-review.jsp");
		dispatcher.forward(req, resp);
	}
}
