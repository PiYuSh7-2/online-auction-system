<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.onlineauction.model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Place Bid</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container">
    <h1>Place Your Bid</h1>
    
    <% 
        // Validate the session
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return; // Stop further execution
        }

        // Retrieve the product from the request attribute
        Product product = (Product) request.getAttribute("product");
        if (product == null) {
            out.println("<p>Error: Product details not found.</p>");
            return;
        }
    %>
    
    <h2><%= product.getName() %></h2>
    <p><%= product.getDescription() %></p>
    <p>Starting Price: <%= product.getStartingPrice() %></p>
    <form action="BidServlet" method="post">
        <input type="hidden" name="productId" value="<%= product.getId() %>">
        <label>Your Bid Amount:</label>
        <input type="number" name="bidAmount" step="0.01" required><br><br>
        <button type="submit">Place Bid</button>
    </form>
    <p><a href="product_list.jsp">Back to Listing</a></p>
</div>
</body>
</html>




