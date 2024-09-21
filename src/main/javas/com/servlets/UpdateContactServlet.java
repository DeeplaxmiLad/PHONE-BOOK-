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

public class UpdateContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("id"));
        String contactName = request.getParameter("contactName");
        String phone = request.getParameter("phone");

        // Hibernate: Update contact
        Session session = FactoryProvider.getFactory().openSession();
        Transaction tx = session.beginTransaction();

        Contact contact = (Contact) session.get(Contact.class, contactId);
        contact.setContactName(contactName);
        contact.setPhone(phone);

        session.update(contact);
        tx.commit();
        session.close();

        // Redirect to view contacts page
        response.sendRedirect("ContactSuccessServlet");
    }
}
