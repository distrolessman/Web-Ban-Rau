package nongsan.webmvc.controller.admin;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.impl.ProductServiceImpl;

//@WebServlet(urlPatterns = { "/admin/cate/delete" })
public class ProductDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (productService.deleteProduct(id))
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }
}
