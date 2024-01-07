package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class generateOrderServlet extends HttpServlet {
    private static final String MY_ORDER = "/WEB-INF/jsp/order/generateOrder.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        if(loginAccount == null){
            resp.sendRedirect("signonForm");
        }else{
            List<String> cardList = new ArrayList<String>();
            cardList.add("Visa");
            cardList.add("MasterCard");
            cardList.add("American Express");
            session.setAttribute("cardList",cardList);
            Cart cart = (Cart) session.getAttribute("cart");
            Order order = new Order();
            order.initOrder(loginAccount,cart);
            session.setAttribute("order",order);
            req.getRequestDispatcher(MY_ORDER).forward(req,resp);
        }

    }
}
