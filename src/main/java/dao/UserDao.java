package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao {
    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }
    public void update(User user) {
        Transaction tx1=null;
        try {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
            e.printStackTrace();
        }

    }
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }
    public List<User> findAll() {
        //List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        session.close();
        return users;
    }
    public User findUserByLogPas(User user)
    {
        List<User> users = (List<User>)HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From User where login = '"
                        + user.getLogin() + "' and password = '"
                        + user.getPassword()+"'").list();
        if (users.isEmpty())
            return null;
        else
            return users.get(0);
    }
    public User findUserByLog(User user)
    {
        List<User> users = (List<User>)HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From User where login = '"
                        + user.getLogin() + "'").list();
        if (users.isEmpty())
            return null;
        else
            return users.get(0);
    }
}