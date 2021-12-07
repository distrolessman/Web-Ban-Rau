package nongsan.webmvc.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nongsan.webmvc.model.Item;
import nongsan.webmvc.model.Order;

import nongsan.webmvc.model.Transaction;
import nongsan.webmvc.service.IOrderedService;
import nongsan.webmvc.service.ITransactionService;
import nongsan.webmvc.service.impl.OrderedServiceImpl;
import nongsan.webmvc.service.impl.TransactionServicesImpl;

public class TransactionController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ITransactionService transactionService;
    @Inject
    IOrderedService orderedService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/checkout.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String trUserSession = req.getParameter("transaction_usersession");
        String trUserName = req.getParameter("transaction_name");
        String trUserMail = req.getParameter("transaction_email");
        String trUserPhone = req.getParameter("transaction_phone");
        String trUserAddress = req.getParameter("transaction_address");
        String trUserMessage = req.getParameter("transaction_mess");
        String trAmount = req.getParameter("transaction_amount");
        String trPayment = req.getParameter("transaction_payment");
        String trCreated = req.getParameter("transaction_created");

        if(!transactionService.createTransaction(trUserSession, trUserName, trUserMail, trUserPhone,
                trUserAddress, trUserMessage, trAmount, trPayment, trCreated)){
            resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
            return;
        }

        int maxId = 0;
        List<Transaction> transactions = transactionService.findAllTransaction();
        if (transactions.size() == 0) {
            maxId = 0;
        } else {
            for (Transaction transactions2 : transactions) {
                if (transactions2.getId() >= maxId)
                    maxId = transactions2.getId();
            }
        }
        HttpSession session = req.getSession(true);
        Order order = (Order) session.getAttribute("order");
        List<Item> listItems = order.getItems();
        for (Item item : listItems) {
            orderedService.createOrdered(item.getProduct().getId(), maxId,
                    item.getQty());
        }
        if (session != null) {
            session.removeAttribute("order"); //remove session
            session.removeAttribute("sumprice"); //remove session
            session.removeAttribute("length_order"); //remove session
        }
        resp.sendRedirect(req.getContextPath() + "/view/client/checkout");
    }
}
