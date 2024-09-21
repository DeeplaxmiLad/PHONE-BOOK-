package com.servlets;

import com.entities.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("id"));

        Session session = null;
        Transaction tx = null;
        try {
            session = FactoryProvider.getFactory().openSession();
            tx = session.beginTransaction();

            // Fetch and delete contact
            Contact contact = session.get(Contact.class, contactId);
            if (contact != null) {
                session.delete(contact);
                tx.commit();
            }

            // Redirect to ContactSuccessServlet to view the updated contact list
            response.sendRedirect("ContactSuccessServlet");

        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to an error page or show an error message if something goes wrong
            response.sendRedirect("error.jsp?error=Failed to delete contact");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
