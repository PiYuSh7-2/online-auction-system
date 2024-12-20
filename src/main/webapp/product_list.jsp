<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.onlineauction.model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Listing</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
    <h1>Active Auctions</h1>

    <% 
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return; 
        }

        // Use type-safe casting with a check
        Object productsObj = request.getAttribute("products");
        if (productsObj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Product> products = (List<Product>) productsObj;
    %>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Starting Price</th>
            <th>End Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% 
            for (Product product : products) {
        %>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getStartingPrice() %></td>
            <td><%= product.getEndDate() %></td>
            <td><a href="product_detail.jsp?id=<%= product.getId() %>">View Details</a></td>
        </tr>
        <% } } else { %>
        <tr>
            <td colspan="5">No products available.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <p><a href="logout.jsp">Logout</a></p>
</div>
</body>
</html>
