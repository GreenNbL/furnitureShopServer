package dao;

import models.Order;
import models.Provider;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class OrderDao {
    public Order findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Order.class, id);
    }
    public void save(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            Transaction tx1 = session.beginTransaction();
            session.save(order);
            tx1.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        session.close();
        }
    }
    public void update(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
    }
    public void delete(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
    }
    public List<Order> findAll() {
        List<Order> orders = (List<Order>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Order").list();
        return orders;
    }
    public List<Order> findAllByUserId(int id) {
        List<Order> orders = (List<Order>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Order where id_user="+id).list();
        return orders;
    }
}
