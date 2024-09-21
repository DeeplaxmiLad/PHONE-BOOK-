<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.entities.Contact" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact List</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 20px auto;
        }
        .edit-btn, .delete-btn, .add-btn {
            text-decoration: none;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
        }
        .edit-btn {
            background-color: #4CAF50;
        }
        .edit-btn:hover {
            background-color: #45a049;
        }
        .delete-btn {
            background-color: #f44336;
        }
        .delete-btn:hover {
            background-color: #e53935;
        }
        .add-btn {
            background-color: #2196F3;
            display: block;
            width: 200px;
            text-align: center;
            margin-top: 20px;
        }
        .add-btn:hover {
            background-color: #1976D2;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Saved Contacts</h2>
        
        <% 
            // Retrieve contacts from request scope set by ContactSuccessServlet
            List<Contact> contactList = (List<Contact>) request.getAttribute("contacts");
            
            if (contactList != null) {
                if (contactList.isEmpty()) {
        %>
        <p>No contacts found.</p>
        <% 
                } else {
        %>
        <table>
            <thead>
                <tr>
                    <th>Contact Name</th>
                    <th>Phone Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Contact contact : contactList) {
                %>
                <tr>
                    <td><%= contact.getContactName() %></td>
                    <td><%= contact.getPhone() %></td>
                    <td>
                        <a class="edit-btn" href="editcontact.jsp?id=<%= contact.getId() %>">Edit</a>
                        <a class="delete-btn" href="DeleteContactServlet?id=<%= contact.getId() %>">Delete</a>
                    </td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
        <% 
                }
            } else {
        %>
        <p>No contacts found.</p>
        <% 
            }
        %>
        
        <a class="add-btn" href="addcontact.jsp">Add New Contact</a>
    </div>

</body>
</html>
