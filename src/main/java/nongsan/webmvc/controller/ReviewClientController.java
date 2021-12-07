package nongsan.webmvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Review;
import nongsan.webmvc.service.IReviewService;
import nongsan.webmvc.service.impl.ReviewServicesImpl;

public class ReviewClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IReviewService reviewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/view/client/product-detail?id=" + req.getParameter("id"));
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String productId = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String content = req.getParameter("content");
        if (reviewService.createReview( name, email, productId, content))
            resp.sendRedirect(req.getContextPath() + "/view/client/product-detail?id=" + productId);
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
    }
}
