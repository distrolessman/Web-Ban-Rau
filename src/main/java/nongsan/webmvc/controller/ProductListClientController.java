package nongsan.webmvc.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.impl.CategoryServicesImpl;
import nongsan.webmvc.service.impl.ProductServiceImpl;

public class ProductListClientController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ICategoryService categoryService;
    @Inject
    IProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String id = req.getParameter("id");
        List<Catalog> cateList = categoryService.findAllCategory();
        req.setAttribute("catelist", cateList);
        List<Product> listProducts = productService.findAllProduct();
        req.setAttribute("listProducts", listProducts);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product.jsp");
        dispatcher.forward(req, resp);
    }
}
