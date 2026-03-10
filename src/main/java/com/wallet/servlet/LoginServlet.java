package com.wallet.servlet;

import com.wallet.model.User;
import com.wallet.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userService.login(email, password);

            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            response.getWriter().println("Login Successful");

        } catch (Exception e) {
            response.getWriter().println(e);
        }

    }
}
