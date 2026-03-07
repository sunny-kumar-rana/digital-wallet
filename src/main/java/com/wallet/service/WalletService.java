package com.wallet.service;

import com.wallet.dao.TransactionDao;
import com.wallet.dao.WalletDao;
import com.wallet.model.Transaction;
import com.wallet.model.Wallet;
import com.wallet.util.DBConnection.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class WalletService {
    private WalletDao walletDao = new WalletDao();
    private TransactionDao  transactionDao = new TransactionDao();

    public void transfer(long senderId, long receiverId, BigDecimal amount) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();

        try{
            conn.setAutoCommit(false);

            Wallet sender = walletDao.findByUserIdForUpdate(conn,senderId);
            Wallet receiver = walletDao.findByUserIdForUpdate(conn,receiverId);

            if(sender.getBalance().compareTo(amount) < 0){
                throw new RuntimeException("Insufficient Balance!");
            }
            walletDao.updateBalance(conn, senderId, sender.getBalance().subtract(amount));
            walletDao.updateBalance(conn, receiverId, receiver.getBalance().add(amount));

            transactionDao.createTransaction(conn, senderId, receiverId, amount, "SUCCESS");
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
        }
    }
}
