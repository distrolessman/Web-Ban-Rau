package nongsan.webmvc.service.impl;

import java.util.List;

import nongsan.webmvc.dao.ITransactionDAO;
import nongsan.webmvc.dao.impl.TransactionDAOImpl;
import nongsan.webmvc.model.Transaction;
import nongsan.webmvc.service.ITransactionService;

import javax.inject.Inject;

public class TransactionServicesImpl implements ITransactionService {
    @Inject
    ITransactionDAO transactionDAO;

    public Boolean createTransaction(String trUserSession, String trUserName, String trUserMail, String trUserPhone,
                                     String trUserAddress, String trUserMessage, String trAmount, String trPayment,
                                     String trCreated) {
        try {
            Transaction transaction = new Transaction();
            transaction.setUser_session(trUserSession);
            transaction.setUser_name(trUserName);
            transaction.setUser_mail(trUserMail);
            transaction.setUser_phone(trUserPhone);
            transaction.setAddress(trUserAddress);
            transaction.setMessage(trUserMessage);
            transaction.setAmount(trAmount);
            transaction.setPayment(trPayment);
            transaction.setCreated(trCreated);
            return transactionDAO.createTransaction(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean updateTransaction(String orderId, String orderName, String orderMail,
                                     String orderPhone, String orderAddress, String orderMessage,
                                     String orderAmount, String orderPayment, String orderStatus) {
        try {
            Transaction oldTransaction = transactionDAO.findTransactionById(Integer.parseInt(orderId));
            oldTransaction.setUser_name(orderName);
            oldTransaction.setUser_mail(orderMail);
            oldTransaction.setUser_phone(orderPhone);
            oldTransaction.setAddress(orderAddress);
            oldTransaction.setMessage(orderMessage);
            oldTransaction.setAmount(orderAmount);
            oldTransaction.setPayment(orderPayment);
            oldTransaction.setStatus(orderStatus);
            return transactionDAO.updateTransaction(oldTransaction);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteTransaction(String id) {
        try {
            return transactionDAO.deleteTransaction(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Transaction findTransactionById(String id) {
        try {
            return transactionDAO.findTransactionById(Integer.getInteger(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return transactionDAO.getAllTransaction();
    }
}
