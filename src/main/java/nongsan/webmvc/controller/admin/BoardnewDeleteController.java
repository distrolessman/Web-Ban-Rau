package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.IBoardNewService;

//@WebServlet(urlPatterns = { "/admin/new/delete" })
public class BoardnewDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Inject
	IBoardNewService boardNewService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(boardNewService.deleteBoardNew(id))
			resp.sendRedirect(req.getContextPath() + "/admin/new/list");
		else
			resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
	}
}