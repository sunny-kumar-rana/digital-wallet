package com.wallet.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.math.BigDecimal;

import com.wallet.service.WalletService;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    private WalletService walletService = new WalletService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            long senderId = Long.parseLong(request.getParameter("senderId"));
            long receiverId = Long.parseLong(request.getParameter("receiverId"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            walletService.transfer(senderId, receiverId, amount);

            response.getWriter().println("Transfer Successful!");
        } catch (Exception e) {
            response.setContentType("text/plain");

            e.printStackTrace(response.getWriter());
        }
    }
}
