package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.User;
import nongsan.webmvc.service.IUserService;
import nongsan.webmvc.service.impl.UserServiceImpl;

public class UserEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_id = req.getParameter("user-id");
        User user = userService.findUserById(user_id);
        if (user != null) {
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/edituser.jsp");
            dispatcher.forward(req, resp);
        } else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String userId = req.getParameter("user-id");
        String userName = req.getParameter("user-name");
        String userEmail = req.getParameter("user-email");
        String userPhone = req.getParameter("user-phone");
        String userUsername = req.getParameter("user-userName");
        String userPassword = req.getParameter("user-password");
        String userCreated = req.getParameter("user-created");
        if (userService.updateUser(userId, userName, userEmail, userPhone,
                userUsername, userPassword, userCreated))
            resp.sendRedirect(req.getContextPath() + "/admin/user/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
    }
}
