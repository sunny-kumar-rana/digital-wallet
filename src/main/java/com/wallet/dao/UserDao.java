package com.wallet.dao;

import com.wallet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public long createUser(Connection conn, User user) throws SQLException{
        String query = "INSERT INTO users (id, name, email, password, created_at) VALUES (user_seq.NEXTVAL, ?, ?, ?, CURRENT_TIMESTAMP)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();

        ResultSet rs = conn.createStatement().executeQuery("SELECT user_seq.CURRVAL FROM dual");
        rs.next();

        return rs.getLong(1);
    }
}
