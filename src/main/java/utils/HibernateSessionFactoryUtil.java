package utils;


import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static String configFileName = "hibernate.cfg.xml";

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = (new Configuration()).configure();
                configuration.addAnnotatedClass(Delivery.class);
                configuration.addAnnotatedClass(Furniture.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Provider.class);
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = (new StandardServiceRegistryBuilder()).configure(configFileName);
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception var2) {
                System.out.println("Исключение!" + var2);
            }
        }

        return sessionFactory;
    }
}