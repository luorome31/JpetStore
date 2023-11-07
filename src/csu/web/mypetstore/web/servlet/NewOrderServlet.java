package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    OrderService orderService  = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String shippingAddressRequired = req.getParameter("shippingAddressRequired");
        String confirmed = req.getParameter("confirmed");
        if (shippingAddressRequired!=null&&shippingAddressRequired.equals("true")){
            req.getRequestDispatcher(SHIPPING).forward(req,resp);
        } else if(confirmed == null||confirmed.equals("false")){
            req.getRequestDispatcher(CONFIRM_ORDER).forward(req,resp);
        } else {
            HttpSession session = req.getSession();
            Order order = (Order) session.getAttribute("order");
            orderService.insertOrder(order);
            session.removeAttribute("cart");
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }
    }
}
