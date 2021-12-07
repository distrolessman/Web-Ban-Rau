package nongsan.webmvc.service;

import java.util.List;

import nongsan.webmvc.model.Transaction;

public interface ITransactionService {
    Boolean createTransaction(String trUserSession, String trUserName, String trUserMail, String trUserPhone,
                              String trUserAddress, String trUserMessage, String trAmount, String trPayment,
                              String trCreated);

    Boolean updateTransaction(String orderId, String orderName, String orderMail,
                              String orderPhone, String orderAddress, String orderMessage,
                              String orderAmount, String orderPayment, String orderStatus);

    Boolean deleteTransaction(String id);

    Transaction findTransactionById(String id);

    List<Transaction> findAllTransaction();
}
