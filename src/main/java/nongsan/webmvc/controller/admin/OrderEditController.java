package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Catalog;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.model.Transaction;
import nongsan.webmvc.service.ICategoryService;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.ITransactionService;
import nongsan.webmvc.service.impl.CategoryServicesImpl;
import nongsan.webmvc.service.impl.ProductServiceImpl;
import nongsan.webmvc.service.impl.TransactionServicesImpl;

public class OrderEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ITransactionService transactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Transaction transaction = transactionService.findTransactionById(id);
        req.setAttribute("order", transaction);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editorder.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String orderId = req.getParameter("order-id");
        String orderName = req.getParameter("order-name");
        String orderMail = req.getParameter("order-mail");
        String orderPhone = req.getParameter("order-phone");
        String orderAddress = req.getParameter("order-address");
        String orderMessage = req.getParameter("order-mess");
        String orderAmount = req.getParameter("order-amount");
        String orderPayment = req.getParameter("order-payment");
        String orderStatus = req.getParameter("order-status");
        if(transactionService.updateTransaction(orderId, orderName, orderMail, orderPhone,
                orderAddress, orderMessage, orderAmount, orderPayment, orderStatus))
            resp.sendRedirect(req.getContextPath() + "/admin/order/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }
}
