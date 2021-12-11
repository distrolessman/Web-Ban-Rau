package nongsan.webmvc.dao.impl;

import nongsan.webmvc.dao.IBaseDAO;
import nongsan.webmvc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BaseDAO<T> implements IBaseDAO<T> {
    private Class<T> type = (Class<T>) this.getClass();
    protected static final SessionFactory FACTORY;

    static {
        FACTORY = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<T> findAll() {
        List<T> result = null;
        Session session = null;
        try {
            session = FACTORY.openSession();
            result = session.createQuery("from " + type.getSimpleName()).list();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    @Override
    public T findById(Integer id) {
        T obj = null;
        Session session = null;
        try {
            session = FACTORY.openSession();
            obj = session.get(type, id);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    @Override
    public Boolean save(T object) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FACTORY.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null)
                session.close();
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FACTORY.openSession();
            T object = session.get(type, id);
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null)
                session.close();
        }
        return true;
    }

    @Override
    public Long count(String sql, Object... parameters) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Long result = (Long) session.createQuery("Select count(*) from " + type.getName()).uniqueResult();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return 0L;
    }

    @Override
    public List<T> queryHibernate(String sql, T object) {
        List<T> listObject = null;
        Session session = null;
        try {
            session = FACTORY.openSession();
            if (object != null) {
                listObject = session.createQuery(sql).setProperties(object).getResultList();
            } else {
                listObject = session.createQuery(sql).list();
            }
            return listObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
