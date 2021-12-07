package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.ITransactionService;
import nongsan.webmvc.service.impl.TransactionServicesImpl;


public class OrderDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ITransactionService transactionService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (transactionService.deleteTransaction(id))
            resp.sendRedirect(req.getContextPath() + "/admin/order/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }
}
