package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.Ordered;

public interface IOrderedDAO extends IBaseDAO<Ordered> {
    Boolean createOrdered(Ordered ordered);

    Boolean updateOrdered(Ordered ordered);

    Boolean deleteOrdered(Integer id);

    Ordered findOrderedById(Integer id);

    List<Ordered> findAllOrdered();
}
