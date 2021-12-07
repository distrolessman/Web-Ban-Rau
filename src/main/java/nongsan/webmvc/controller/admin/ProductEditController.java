package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Id;
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

public class ProductEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IProductService productService;
    @Inject
    ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Catalog> cateList = categoryService.findAllCategory();
        req.setAttribute("catelist", cateList);
        String id = req.getParameter("id");
        Product product = productService.findProductById(id);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editproduct.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String id = req.getParameter("product-sku");
        String productCategory = req.getParameter("product-cate");
        String productName = req.getParameter("product-name");
        String productPrice = req.getParameter("product-price");
        String productStatus = req.getParameter("product-status");
        String productDescription = req.getParameter("product-desc");
        String productContent = req.getParameter("product-content");
        String productDiscount = req.getParameter("product-discount");
        String productImage = req.getParameter("product-image");
        String productCreated = req.getParameter("product-day");

        if (productService.updateProduct(id, productCategory, productName, productPrice,
                productStatus, productDescription, productContent,
                productDiscount, productImage, productCreated))
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }
}