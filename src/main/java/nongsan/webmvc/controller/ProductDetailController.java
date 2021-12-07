package nongsan.webmvc.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Product;
import nongsan.webmvc.model.Review;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.IReviewService;

@WebServlet(urlPatterns = {"/view/client/product-detail"})
public class ProductDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IProductService productService;
    @Inject
    IReviewService reviewService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product productDetails = productService.findProductById(id);

        req.setAttribute("productDetails", productDetails);
        req.setAttribute("name_cate_of_product", productDetails.getCatalog().getName());

        Integer idCate = productDetails.getCatalog().getId();
        List<Product> listProductsSameCategory = productService.findProductByCategoryId(idCate);
        req.setAttribute("listProductsSameCategory", listProductsSameCategory);

        List<Review> listReviewsByProduct = reviewService.findReviewByProductId(id);
        req.setAttribute("listReviewsByProduct", listReviewsByProduct);

        List<Product> listProducts = productService.findAllProduct();
        req.setAttribute("listProducts", listProducts);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product-detail.jsp");
        dispatcher.forward(req, resp);
    }
}
