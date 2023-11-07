package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {
    OrderService orderService = new OrderService();
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        // 获取订单ID
        String orderIdParam = req.getParameter("orderId");

        if (orderIdParam != null) {
            int orderId = Integer.parseInt(orderIdParam);

            Order order = orderService.getOrder(orderId);

            if (loginAccount.getUsername().equals(order.getUsername())) {
                session.setAttribute("order", order);
                req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
            } else {
                resp.getWriter().write("You may only view your own orders.");
            }
        } else {
            resp.getWriter().write("Invalid Order ID");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
