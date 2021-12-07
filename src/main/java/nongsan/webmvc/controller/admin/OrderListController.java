package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.Transaction;
import nongsan.webmvc.service.ITransactionService;
import nongsan.webmvc.service.impl.TransactionServicesImpl;

public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	ITransactionService transactionService;
 
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		List<Transaction> transactionList = transactionService.findAllTransaction();
		req.setAttribute("order", transactionList); 
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-order.jsp"); 
		dispatcher.forward(req, resp); 
	}
}
