package com.example.demofx.dao;

import com.example.demofx.models.OrdersProductsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOOrdersProductsHql {
    private static DAOOrdersProductsHql instance = null;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session;

    public static void addNewOrderProduct(OrdersProductsEntity _orderProduct) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(_orderProduct);
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

    public DAOOrdersProductsHql() {
    }

    public static DAOOrdersProductsHql getInstance() {
        if (instance == null) {
            instance = new DAOOrdersProductsHql();
        }
        return instance;
    }
}

