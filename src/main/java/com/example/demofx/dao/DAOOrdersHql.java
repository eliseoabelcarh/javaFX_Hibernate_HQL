package com.example.demofx.dao;

import com.example.demofx.models.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOOrdersHql {

    private static DAOOrdersHql instance = null;

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session;


    public static void addNewOrder(OrdersEntity _order) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(_order);
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
    private DAOOrdersHql() {
    }

    public static DAOOrdersHql getInstance() {
        if (instance == null) {
            instance = new DAOOrdersHql();
        }

        return instance;
    }
}
