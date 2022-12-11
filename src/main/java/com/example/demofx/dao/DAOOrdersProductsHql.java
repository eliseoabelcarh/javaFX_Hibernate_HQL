package com.example.demofx.dao;

import com.example.demofx.models.OrdersProductsEntity;
import com.example.demofx.models.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

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

    public static List<OrdersProductsEntity> getAllOrdersProducts() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Transaction started");
        Query query = session.createQuery("FROM OrdersProductsEntity");
        System.out.println("Query created");
        List<OrdersProductsEntity> ordersProducts = query.list();
        session.getTransaction().commit();
        System.out.println("Transaction committed");
        session.close();
        return ordersProducts;
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

