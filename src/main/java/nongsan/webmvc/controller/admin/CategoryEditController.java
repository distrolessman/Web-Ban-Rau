package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.impl.CategoryServicesImpl;

public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Catalog category = categoryService.findCategoryById(id);
        if (category != null) {
            req.setAttribute("catelist", category);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editcate.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String id = req.getParameter("id");
        String newName = req.getParameter("name");
        String newParentId = req.getParameter("parent-id");
        if (categoryService.updateCategory(id, newName, newParentId))
            resp.sendRedirect(req.getContextPath() + "/admin/cate/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }
}