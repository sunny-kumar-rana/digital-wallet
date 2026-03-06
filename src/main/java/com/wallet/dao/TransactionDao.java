package com.wallet.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDao {
    public void createTransaction(Connection conn, long senderId, long receiverId, BigDecimal amount, String status) throws SQLException {
        String query = "INSERT INTO transactions(id, sender_id, receiver_id, amount, status) VALUES (transaction_seq.NEXTVAL,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1,senderId);
        ps.setLong(2,receiverId);
        ps.setBigDecimal(3,amount);
        ps.setString(4,status);

        ps.executeUpdate();
    }
}
