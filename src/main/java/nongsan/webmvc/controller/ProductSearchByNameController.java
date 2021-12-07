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

public class ProductSearchByNameController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ICategoryService categoryService;
    @Inject
    IProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("s");
        List<Catalog> cateList = categoryService.findAllCategory();
        req.setAttribute("catelist", cateList);

        List<Product> productSeachByName = productService.searchByName(name);
        req.setAttribute("listProducts", productSeachByName);

        List<Product> listProducts = productService.findAllProduct();
        req.setAttribute("product_all", listProducts);

        // Product bán chạy
        List<Product> product_banchay = listProducts.stream().filter(p -> p.getCatalog().getId() == 6).collect(Collectors.toList());
        req.setAttribute("product_banchay", product_banchay);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product-search.jsp");
        dispatcher.forward(req, resp);
    }
}
