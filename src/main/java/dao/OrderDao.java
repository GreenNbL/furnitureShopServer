package dao;

import models.Order;
import models.Provider;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.sql.Date;
import java.util.ArrayList;
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
    public List<Order> findAllByFurnitureId(int id) {
        List<Order> orders = (List<Order>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Order where id_furniture="+id).list();
        return orders;
    }
    public List<Order> findAllActiveOrders() {
        List<Order> orders=new ArrayList<Order>();
        try{
        orders = (List<Order>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Order AS o JOIN FETCH o.delivery AS d WHERE d.status = 'доставляется'").list();
    }  catch (Exception e) {
        e.printStackTrace();
    }
        if(orders.isEmpty())
            return null;
        else
            return orders;
    }
    public List<Order> findAllByPeriod(Date startDate, Date endDate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Order> orders =new ArrayList<>();
        try {
            session.beginTransaction();
            // Создаем HQL запрос для выборки по промежутку времени
            String hql = "FROM Order WHERE dateOrder BETWEEN :startDate AND :endDate";
            Query<Order> query = session.createQuery(hql, Order.class);

            // Устанавливаем параметры запроса
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            // Получаем результаты запроса
            orders= query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            if(orders.isEmpty())
                return null;
            else
                return orders;
        }
    }
}
