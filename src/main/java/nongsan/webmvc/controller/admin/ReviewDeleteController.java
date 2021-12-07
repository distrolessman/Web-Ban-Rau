package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.IReviewService;
import nongsan.webmvc.service.impl.ReviewServicesImpl;

public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IReviewService reviewService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(reviewService.deleteReview(id))
			resp.sendRedirect(req.getContextPath() + "/admin/review/list");
		else
			resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
	}
}
