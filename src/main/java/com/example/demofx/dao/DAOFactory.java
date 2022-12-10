package com.example.demofx.dao;

public class DAOFactory {
    private static DAOFactory instance = null;
    private static DAOUsersHql usersDao = null;
    private static DAOProductsHql productsDao = null;

    private static  DAOOrdersHql ordersDao = null;

    private static DAOOrdersProductsHql ordersProductsDao = null;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public DAOUsersHql getUsersDao() {
        if (usersDao == null) {
            usersDao = DAOUsersHql.getInstance();
        }
        return usersDao;
    }


    public DAOProductsHql getProductsDao() {
        if (productsDao == null) {
            productsDao = DAOProductsHql.getInstance();
        }
        return productsDao;
    }

    public DAOOrdersHql getOrdersDao() {
        if (ordersDao == null) {
            ordersDao = DAOOrdersHql.getInstance();
        }
        return ordersDao;
    }

    public DAOOrdersProductsHql getOrdersProductsDao() {
        if (ordersProductsDao == null) {
            ordersProductsDao = DAOOrdersProductsHql.getInstance();
        }
        return ordersProductsDao;
    }
}
