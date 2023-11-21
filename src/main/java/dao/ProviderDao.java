package dao;

import models.Provider;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ProviderDao {
    public Provider findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Provider.class, id);
    }
    public void save(Provider provider) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(provider);
        tx1.commit();
        session.close();
    }
    public void update(Provider provider) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(provider);
        tx1.commit();
        session.close();
    }
    public void delete(Provider provider) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(provider);
        tx1.commit();
        session.close();
    }
    public List<Provider> findAll() {
        List<Provider> providers = (List<Provider>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Provider").list();
        return providers;
    }

}
