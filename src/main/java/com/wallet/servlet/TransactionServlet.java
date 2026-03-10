package com.wallet.servlet;

import com.wallet.model.Transaction;
import com.wallet.service.WalletService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {

    WalletService walletService = new WalletService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{
            long userId = Long.parseLong(request.getParameter("userId"));

            List<Transaction> txs = walletService.getTransactionHistory(userId);

            PrintWriter pw = response.getWriter();
            for(Transaction t : txs){

                pw.println(
                        "Transaction ID : " + t.getId() +
                        "From : " + t.getSenderId() +
                        "To : " + t.getReceiverId() +
                        "Amount : " + t.getAmount()
                );

            }
        } catch (Exception e) {
            response.getWriter().println(e);
        }
    }
}
