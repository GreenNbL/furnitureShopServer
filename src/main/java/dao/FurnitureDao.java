package dao;

import models.Delivery;
import models.Furniture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class FurnitureDao implements Dao<Furniture>{
    public Furniture findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Furniture.class, id);
    }
    public void save(Furniture furniture) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(furniture);
        tx1.commit();
        session.close();
    }
    public void update(Furniture furniture) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(furniture);
        tx1.commit();
        session.close();
    }
    public void delete(Furniture furniture) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(furniture);
        tx1.commit();
        session.close();
    }
    public List<Furniture> findAll() {
        List<Furniture> furnitures=new ArrayList<Furniture>();
        try {
            furnitures = (List<Furniture>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Furniture").list();
        }  catch (Exception e) {
        e.printStackTrace();
        }
        return furnitures;
    }
}
