package com.servlets;

import com.entities.Contact;
import com.servlets.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Fetch form data
            String contactName = request.getParameter("contactName");
            String phone = request.getParameter("phone");

            // Create contact object
            Contact contact = new Contact(contactName, phone);

            // Hibernate: Save contact in database
            Session session = FactoryProvider.getFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(contact);
            tx.commit();
            session.close();

            // Redirect to view contacts page
            response.sendRedirect("ContactSuccessServlet");
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect back to add contact page if an error occurs
            response.sendRedirect("addcontact.jsp?error=Failed to add contact");
        }
    }
}
