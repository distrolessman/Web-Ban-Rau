package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.impl.ProductServiceImpl;

public class ProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listProducts = productService.findAllProduct();
        req.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-product.jsp");
        dispatcher.forward(req, resp);
    }
}
