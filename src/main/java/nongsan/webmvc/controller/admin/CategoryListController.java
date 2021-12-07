package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.impl.CategoryServicesImpl;

public class CategoryListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Catalog> listCategories = categoryService.findAllCategory();
        req.setAttribute("catelist", listCategories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-cate.jsp");
        dispatcher.forward(req, resp);
    }
}