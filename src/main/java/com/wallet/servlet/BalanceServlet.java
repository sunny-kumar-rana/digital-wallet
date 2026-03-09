package com.wallet.servlet;


import com.wallet.service.WalletService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {
    private WalletService walletService = new WalletService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        try{
            long userId = Long.parseLong(request.getParameter("userId"));
            BigDecimal balance = walletService.getBalance(userId);

            PrintWriter pw = response.getWriter();
            pw.println("Balance = " + balance);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
