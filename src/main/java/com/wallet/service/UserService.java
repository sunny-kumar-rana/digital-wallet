package com.wallet.service;

import com.wallet.dao.UserDao;
import com.wallet.dao.WalletDao;
import com.wallet.model.User;
import com.wallet.util.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private UserDao userDao = new UserDao();
    private WalletDao walletDao = new WalletDao();

    public void register(User user) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();

        try{
            conn.setAutoCommit(false);

            long userId = userDao.createUser(conn, user);
            walletDao.createWallet(conn, userId);
            conn.commit();
        } catch (Exception e){
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    public User login(String email, String password) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();

        try{
            User user = userDao.findUserByEmail(conn, email);

            if(user == null){
                throw new RuntimeException("User not Found");
            }
            if(!user.getPassword().equals(password)){
                throw new RuntimeException("Invalid Password");
            }

            return user;
        } finally {
            conn.close();
        }
    }
}
