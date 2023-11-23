package dao;

import models.Delivery;
import models.Furniture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DeliveryDao {
    public Delivery findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Delivery.class, id);
    }
    public void save(Delivery delivery) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(delivery);
        tx1.commit();
        session.close();
    }
    public void update(Delivery delivery) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
        Transaction tx1 = session.beginTransaction();
        session.update(delivery);
        tx1.commit();
        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
    public void delete(Delivery delivery) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(delivery);
        tx1.commit();
        session.close();
    }
    public List<Delivery> findAll() {
        List<Delivery> deliveries = (List<Delivery>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Delivery").list();
        return deliveries;
    }
}
