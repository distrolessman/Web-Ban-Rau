package nongsan.webmvc.controller;

import nongsan.webmvc.model.User;
import nongsan.webmvc.service.IUserService;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IUserService userService;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = session.getAttribute("username").toString();
        User user = userService.findUserByUsername(username);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/checkout.jsp");
        dispatcher.forward(req, resp);
    }
}
