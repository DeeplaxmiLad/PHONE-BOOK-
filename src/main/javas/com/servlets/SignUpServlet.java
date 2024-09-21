package com.servlets;

import com.entities.User;
import com.servlets.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Fetch user data
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Create user object
            User user = new User(username, email, password);

            // Hibernate: Save user to the database
            Session session = FactoryProvider.getFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();

            // Redirect to the add contact page
            response.sendRedirect("addcontact.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=Signup Failed");
        }
    }
}
