package nongsan.webmvc.dao.impl;

import java.util.List;

import nongsan.webmvc.dao.ITransactionDAO;
import nongsan.webmvc.model.Transaction;

public class TransactionDAOImpl extends BaseDAO<Transaction> implements ITransactionDAO {

    public TransactionDAOImpl() {
        super();
        setType(Transaction.class);
    }

    public Boolean createTransaction(Transaction transaction) {
        return save(transaction);
    }

    @Override
    public Boolean deleteTransaction(Integer id) {
        return delete(id);
    }

    @Override
    public Transaction findTransactionById(Integer id) {
        return findById(id);
    }

    @Override
    public Boolean updateTransaction(Transaction transaction) {
        return save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return findAll();
    }
}
