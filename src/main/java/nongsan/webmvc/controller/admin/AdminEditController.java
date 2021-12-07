package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Admin;
import nongsan.webmvc.service.IAdminService;
import nongsan.webmvc.service.impl.AdminServicesImpl;

public class AdminEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IAdminService adminService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String admin_id = req.getParameter("id");
        Admin admin = adminService.findAdminById(admin_id);
        if (admin != null) {
            req.setAttribute("admin", admin);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editadmin.jsp");
            dispatcher.forward(req, resp);
        } else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String admin_username = req.getParameter("username");
        String admin_password = req.getParameter("password");
        String admin_name = req.getParameter("name");
        String admin_id = req.getParameter("id");
        if (adminService.updateAdminInfo(admin_id, admin_username, admin_password, admin_name))
            resp.sendRedirect(req.getContextPath() + "/admin/admin/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }
}
