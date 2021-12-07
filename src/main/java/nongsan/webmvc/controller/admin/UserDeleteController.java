package nongsan.webmvc.controller.admin;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.IUserService;
import nongsan.webmvc.service.impl.UserServiceImpl;

public class UserDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_id = req.getParameter("user-id");
        if (userService.deleteUser(user_id)) {
            req.setAttribute("userList", userService.findAllUser());
            RequestDispatcher dispatcherUser = req.getRequestDispatcher("/view/admin/user.jsp");
            dispatcherUser.forward(req, resp);
        } else
            resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
    }
}