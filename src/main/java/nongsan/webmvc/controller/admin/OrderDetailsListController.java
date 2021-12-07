package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Ordered;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.IOrderedService;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.impl.OrderedServiceImpl;
import nongsan.webmvc.service.impl.ProductServiceImpl;

public class OrderDetailsListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IOrderedService orderedService;
    @Inject
    IProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ordered> orderedList = orderedService.findAllOrdered();
        req.setAttribute("orderedlist", orderedList);
        List<Product> products = new ArrayList<>();
        for (Ordered ordered : orderedList) {
            products.add(ordered.getProduct());
        }
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-orderdetail.jsp");
        dispatcher.forward(req, resp);
    }
}
