package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nongsan.webmvc.dao.IAdminDAO;
import nongsan.webmvc.model.Admin;
import nongsan.webmvc.service.IAdminService;

@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IAdminService adminService;
    public AdminLoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/admin/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("admin-username");
        String password = request.getParameter("admin-password");
        Admin admin = new Admin();
        admin.setName(request.getParameter("name"));

        if (adminService.checkLoginAdmin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin-username", username);
            session.setAttribute("admin-password", password);
            response.sendRedirect(request.getContextPath() + "/admin/homepage");
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc email đã tồn tại, vui lòng chọn giá trị khác!");
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/login.jsp");
            rd.forward(request, response);
        }
    }
}
