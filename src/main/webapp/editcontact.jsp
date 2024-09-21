<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="com.servlets.FactoryProvider" %>
<%@ page import="com.entities.Contact" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Contact</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .contact-container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px #000;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .input-group {
            margin-bottom: 15px;
        }
        .input-group label {
            display: block;
            font-weight: bold;
        }
        .input-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="contact-container">
        <h2>Edit Contact</h2>
        <%
            // Declare and initialize variables for session and transaction
            Contact contact = null;
            Session hibernateSession = null;
            Transaction tx = null;

            try {
                // Parse contactId from request
                int contactId = Integer.parseInt(request.getParameter("id"));
                
                // Open Hibernate session and begin transaction
                hibernateSession = FactoryProvider.getFactory().openSession();
                tx = hibernateSession.beginTransaction();
                
                // Retrieve contact from database
                contact = hibernateSession.get(Contact.class, contactId);
                
                // Commit transaction
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Failed to retrieve contact details!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return; // Exit the JSP processing if an error occurs
            } finally {
                // Ensure session is closed
                if (hibernateSession != null) {
                    hibernateSession.close();
                }
            }
            
            if (contact == null) {
                out.println("<p>Contact not found!</p>");
            } else {
        %>
        <form action="UpdateContactServlet" method="post">
    <input type="hidden" name="id" value="<%= contact.getId() %>">
    <div class="input-group">
        <label for="contactName">Contact Name:</label>
        <input type="text" id="contactName" name="contactName" value="<%= contact.getContactName() %>" required>
    </div>
    <div class="input-group">
        <label for="phone">Phone Number:</label>
        <input type="text" id="phone" name="phone" value="<%= contact.getPhone() %>" required>
    </div>
    <input type="submit" value="Update Contact" class="submit-btn">
</form>

        <% 
            }
        %>
    </div>

</body>
</html>
