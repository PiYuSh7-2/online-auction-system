package com.onlineauction.servlet;

import com.onlineauction.dao.BidDAO;
import com.onlineauction.model.Bid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/BidServlet")
public class BidServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));
        double bidAmount = Double.parseDouble(request.getParameter("bidAmount"));

        Bid bid = new Bid();
        bid.setProductId(productId);
        bid.setUserId((int) session.getAttribute("userId")); // Ensure userId is stored during login
        bid.setBidAmount(bidAmount);
        bid.setBidTime(new Timestamp(System.currentTimeMillis()));

        BidDAO bidDAO = new BidDAO();
        boolean isBidPlaced = bidDAO.placeBid(bid);

        if (isBidPlaced) {
            response.sendRedirect("product_list.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to place bid. Ensure the bid is higher than the current bid.");
            request.getRequestDispatcher("bid.jsp").forward(request, response);
        }
    }
}

