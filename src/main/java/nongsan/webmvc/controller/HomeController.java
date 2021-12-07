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

import nongsan.webmvc.model.BoardNew;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.IBoardNewService;
import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.impl.BoardNewServicesImpl;
import nongsan.webmvc.service.impl.CategoryServicesImpl;

import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IProductService productService;
    @Inject
    IBoardNewService boardNewService;
    @Inject
    ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardNew> BoardNewList = boardNewService.findAllBoardNew();
        req.setAttribute("BoardNewlist", BoardNewList);

        List<Product> listProducts = productService.findAllProduct();
        req.setAttribute("listProducts", listProducts);

        // Product Rau củ quả
        List<Product> product_raucu = listProducts.stream().filter(p -> p.getCatalog().getId() == 1).collect(Collectors.toList());
        req.setAttribute("product_raucu", product_raucu);

        // Product hạt
        List<Product> product_hat = listProducts.stream().filter(p -> p.getCatalog().getId() == 2).collect(Collectors.toList());
        req.setAttribute("product_hat", product_hat);

        // Product trái cây
        List<Product> product_traicay = listProducts.stream().filter(p -> p.getCatalog().getId() == 3).collect(Collectors.toList());
        req.setAttribute("product_traicay", product_traicay);

        // Product mật ong
        List<Product> product_matong = listProducts.stream().filter(p -> p.getCatalog().getId() == 4).collect(Collectors.toList());
        req.setAttribute("product_matong", product_matong);

        // Product mới
        List<Product> product_new = listProducts.stream().filter(p -> p.getCatalog().getId() == 5).collect(Collectors.toList());
        req.setAttribute("product_new", product_new);

        // Product bán chạy
        List<Product> product_banchay = listProducts.stream().filter(p -> p.getCatalog().getId() == 6).collect(Collectors.toList());
        req.setAttribute("product_banchay", product_banchay);

        // Product giảm giá
        List<Product> product_sale = listProducts.stream().filter(p -> p.getCatalog().getId() == 7).collect(Collectors.toList());
        req.setAttribute("product_sale", product_sale);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/index.jsp");
        dispatcher.forward(req, resp);
    }
}
