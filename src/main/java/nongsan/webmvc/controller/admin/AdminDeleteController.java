package nongsan.webmvc.controller.admin;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.IAdminService;
import nongsan.webmvc.service.impl.AdminServicesImpl; 

public class AdminDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IAdminService adminService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	   
		String admin_id = req.getParameter("admin-id");
		if(adminService.deleteAdmin(admin_id)){
			req.setAttribute("adminlist", adminService.findAllAdmin());
			RequestDispatcher dispatcherUser  = req.getRequestDispatcher("/view/admin/admin.jsp");
			dispatcherUser.forward(req, resp);
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
		}
	}
}

