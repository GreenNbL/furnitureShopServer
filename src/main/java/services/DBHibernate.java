package services;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
public class DBHibernate {
    public static void main(String[] arg) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            String queryString= "DROP TABLE IF EXISTS `orders`;";
            session.createSQLQuery (queryString).executeUpdate();

            queryString="DROP TABLE IF EXISTS `deliveries`;" ;
            session.createSQLQuery (queryString).executeUpdate();

            queryString=       "DROP TABLE IF EXISTS `furnitures`;" ;
            session.createSQLQuery (queryString).executeUpdate();

            queryString= "DROP TABLE IF EXISTS `providers`;";
            session.createSQLQuery (queryString).executeUpdate();

            queryString= "DROP TABLE IF EXISTS `users`;" ;
            session.createSQLQuery (queryString).executeUpdate();

            queryString=
                    "CREATE TABLE `users` (\n" +
                            "  `id_user` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `login` varchar(45) DEFAULT NULL,\n" +
                            "  `password` varchar(45) DEFAULT NULL,\n" +
                            "  `role` varchar(45) DEFAULT NULL,\n" +
                            "  `surname` varchar(45) DEFAULT NULL,\n" +
                            "  `name` varchar(45) DEFAULT NULL,\n" +
                            "  `tel_number` varchar(45) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`id_user`)\n" +
                            ");";
            session.createSQLQuery (queryString).executeUpdate();

            queryString=
                    "CREATE TABLE `providers` (\n" +
                            "  `id_provider` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `country` varchar(45) DEFAULT NULL,\n" +
                            "  `company` varchar(45) DEFAULT NULL,\n" +
                            "  `email` varchar(45) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`id_provider`)\n" +
                            "); ";
            session.createSQLQuery (queryString).executeUpdate();

            queryString=
                    "CREATE TABLE `furnitures` (\n" +
                            "  `id_furniture` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_provider` int DEFAULT NULL,\n" +
                            "  `name_furniture` varchar(45) DEFAULT NULL,\n" +
                            "  `price` double DEFAULT NULL,\n" +
                            "  `amount_stock` int DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`id_furniture`),\n" +
                            "  KEY `id_provider_idx` (`id_provider`),\n" +
                            "  CONSTRAINT `id_provider` FOREIGN KEY (`id_provider`) REFERENCES `providers` (`id_provider`) ON DELETE SET NULL ON UPDATE CASCADE\n" +
                            ");" ;
            session.createSQLQuery (queryString).executeUpdate();

            queryString=
                    "CREATE TABLE `deliveries` (\n" +
                            "  `id_delivery` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `status` varchar(45) DEFAULT NULL,\n" +
                            "  `date_delivery` date DEFAULT NULL,\n" +
                            "  `adress` varchar(45) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`id_delivery`)\n" +
                            ");\n";
            session.createSQLQuery (queryString).executeUpdate();

            queryString=
                    "CREATE TABLE `orders` (\n" +
                            "  `id_order` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_user` int DEFAULT NULL,\n" +
                            "  `id_furniture` int DEFAULT NULL,\n" +
                            "  `amount` int DEFAULT NULL,\n" +
                            "  `total_cost` double DEFAULT NULL,\n" +
                            "  `date_order` date DEFAULT NULL,\n" +
                            "  `id_delivery` int DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`id_order`),\n" +
                            "  KEY `id_user_idx` (`id_user`),\n" +
                            "  KEY `id_furniture_idx` (`id_furniture`),\n" +
                            "  KEY `id_delivery_idx` (`id_delivery`),\n" +
                            "  CONSTRAINT `id_delivery` FOREIGN KEY (`id_delivery`) REFERENCES `deliveries` (`id_delivery`) ON DELETE SET NULL,\n" +
                            "  CONSTRAINT `id_furniture` FOREIGN KEY (`id_furniture`) REFERENCES `furnitures` (`id_furniture`) ON DELETE SET NULL,\n" +
                            "  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE SET NULL ON UPDATE RESTRICT\n" +
                            ");";
            session.createSQLQuery (queryString).executeUpdate();

            queryString= "INSERT INTO `users` VALUES (1,'admin','92668751','admin','-','-','-');";
            session.createSQLQuery (queryString).executeUpdate();

            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());
        }
    }
}
