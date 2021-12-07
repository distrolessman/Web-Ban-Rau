package nongsan.webmvc.controller.admin;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.impl.CategoryServicesImpl;

//@WebServlet(urlPatterns = { "/admin/cate/delete" })
public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	ICategoryService categoryService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(categoryService.deleteCategory(id))
			resp.sendRedirect(req.getContextPath() + "/admin/cate/list");
		else
			resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
	}
}
