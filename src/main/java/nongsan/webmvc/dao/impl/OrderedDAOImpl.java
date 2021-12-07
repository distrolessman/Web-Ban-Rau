package nongsan.webmvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nongsan.webmvc.dao.IOrderedDAO;
import nongsan.webmvc.model.Ordered;

public class OrderedDAOImpl extends BaseDAO<Ordered> implements IOrderedDAO {

    public OrderedDAOImpl() {
        super();
        setType(Ordered.class);
    }

    @Override
    public Boolean createOrdered(Ordered ordered) {
        return save(ordered);
    }

    @Override
    public Boolean updateOrdered(Ordered ordered) {
        return save(ordered);
    }

    @Override
    public Boolean deleteOrdered(Integer id) {
        return true;
    }

    @Override
    public Ordered findOrderedById(Integer id) {
        return null;
    }

    @Override
    public List<Ordered> findAllOrdered() {
        return findAll();
    }
}
