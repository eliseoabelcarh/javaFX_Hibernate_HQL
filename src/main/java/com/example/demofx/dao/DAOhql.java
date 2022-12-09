package com.example.demofx.dao;

import com.example.demofx.models.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DAOhql {
    private static DAOhql instance = null;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session;

    public static UsersEntity getUserById(int _id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Transaction started");
        Query query = session.createQuery("from UsersEntity where id= :id");
        query.setParameter("id", _id);
        UsersEntity user = (UsersEntity) query.uniqueResult();
        session.getTransaction().commit();
        System.out.println("Transaction committed");
        session.close();
        return user;
    }

    public static UsersEntity getUserByUsername(String _username) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Transaction started");
        Query query = session.createQuery("from UsersEntity where username= :username");
        query.setParameter("username", _username);
        UsersEntity user = (UsersEntity) query.uniqueResult();
        session.getTransaction().commit();
        System.out.println("Transaction -----userfounded-----");
        System.out.println(user);
        System.out.println("------------Transaction committed");
        session.close();
        return user;
    }

    public static void addNewUser(UsersEntity _user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(_user);
        // session.flush();//use it without transaction
        session.getTransaction().commit();
        System.out.println("Transaction committed");
        session.close();
    }

    public static List<UsersEntity> getAllUsers() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Transaction started");
        Query query = session.createQuery("FROM UsersEntity");
        System.out.println("Query created");
        List<UsersEntity> users = query.list();
        session.getTransaction().commit();
        System.out.println("Transaction committed");
        session.close();
        return users;
    }

    public static void close() {
        System.out.println("Closing Session Factory");
        sessionFactory.close();
        System.out.println("Session Factory closed");
    }

    private DAOhql() {
    }

    public static DAOhql getInstance() {
        if (instance == null) {
            instance = new DAOhql();
        }
        return instance;
    }
}
