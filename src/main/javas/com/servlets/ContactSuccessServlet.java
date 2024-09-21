package com.servlets;

import com.entities.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContactSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Session session = null;
        Transaction tx = null;
        try {
            session = FactoryProvider.getFactory().openSession();
            tx = session.beginTransaction();

            // Fetch all contacts from the database
            List<Contact> contacts = session.createQuery("from Contact", Contact.class).list();

            // Debugging statement to check the retrieved contacts
            System.out.println("Retrieved contacts: " + contacts);

            tx.commit();
            
            // Check if the list is empty
            if (contacts.isEmpty()) {
                request.setAttribute("message", "No contacts found.");
            }

            // Set contacts list in request scope and forward to JSP page
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("viewcontacts.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // If error occurs, forward to an error page or retry page
            request.setAttribute("errorMessage", "Failed to retrieve contacts! Please try again.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
