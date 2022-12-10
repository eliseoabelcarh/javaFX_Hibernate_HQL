package com.example.demofx.dao;

import com.example.demofx.models.ProductsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOProductsHql {

    private static DAOProductsHql instance = null;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session;


    public static void addNewProduct(ProductsEntity _product) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(_product);
        // session.flush();//use it without transaction
        session.getTransaction().commit();
        System.out.println("Transaction committed");
        session.close();
    }


    public static void close() {
        System.out.println("Closing Session Factory");
        sessionFactory.close();
        System.out.println("Session Factory closed");
    }
    public DAOProductsHql() {
    }
    public static DAOProductsHql getInstance() {
        if (instance == null) {
            instance = new DAOProductsHql();
        }
        return instance;
    }

}
