package nongsan.webmvc.dao;
import java.util.List;

import nongsan.webmvc.model.Transaction;


public interface ITransactionDAO extends IBaseDAO<Transaction> {
	Boolean createTransaction(Transaction transaction);

	Boolean updateTransaction(Transaction admin);

	Boolean deleteTransaction(Integer id);
 
	Transaction findTransactionById(Integer id);
 
	List<Transaction> getAllTransaction();

}
