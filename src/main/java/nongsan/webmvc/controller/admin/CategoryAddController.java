package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.impl.CategoryServicesImpl;
import nongsan.webmvc.model.Catalog;

//@WebServlet(urlPatterns = { "" })
public class CategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	ICategoryService categoryService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addcate.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
	    String categoryName = req.getParameter("cate-name");
		String categoryParentId = req.getParameter("parent-id");

		if(categoryService.createCategory(categoryName, categoryParentId))
			resp.sendRedirect(req.getContextPath() + "/admin/cate/list");
		else
			resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
	}
}
