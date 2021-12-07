package nongsan.webmvc.service.impl;

import java.util.List;

import nongsan.webmvc.dao.IOrderedDAO;
import nongsan.webmvc.dao.IProductDAO;
import nongsan.webmvc.dao.ITransactionDAO;
import nongsan.webmvc.dao.impl.OrderedDAOImpl;
import nongsan.webmvc.dao.impl.ProductDAOImpl;
import nongsan.webmvc.dao.impl.TransactionDAOImpl;
import nongsan.webmvc.model.Ordered;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.model.Transaction;
import nongsan.webmvc.service.IOrderedService;

import javax.inject.Inject;

public class OrderedServiceImpl implements IOrderedService {
	@Inject
	IOrderedDAO orderedDAO;
	@Inject
	IProductDAO productDAO;
	@Inject
	ITransactionDAO transactionDAO;
	@Override
	public Boolean createOrdered(Integer productId, Integer transactionId, Integer quantity) {
		try{
			Ordered ordered = new Ordered();
			Product product = productDAO.findProductById(productId);
			Transaction transaction = transactionDAO.findTransactionById(transactionId);
			ordered.setProduct(product);
			ordered.setTransaction(transaction);
			ordered.setQty(quantity);
			return orderedDAO.createOrdered(ordered);
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateOrdered(Ordered ordered) {
		return orderedDAO.updateOrdered(ordered);
	}

	@Override
	public Boolean deleteOrdered(String id) {
		return deleteOrdered(id);
	}

	@Override
	public Ordered findOrderedById(Integer id) {
		return orderedDAO.findOrderedById(id);
	}

	@Override
	public List<Ordered> findAllOrdered() {
		return orderedDAO.findAllOrdered();
	}

}
