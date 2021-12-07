package nongsan.webmvc.service;

import java.util.List;

import nongsan.webmvc.model.Ordered;

public interface IOrderedService {
    Boolean createOrdered(Integer productId, Integer transactionId, Integer quantity);

    Boolean updateOrdered(Ordered ordered);

    Boolean deleteOrdered(String id);

    Ordered findOrderedById(Integer id);

    List<Ordered> findAllOrdered();
}
