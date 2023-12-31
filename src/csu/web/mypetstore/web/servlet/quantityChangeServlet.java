package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class quantityChangeServlet extends HttpServlet {
    CatalogService service=new CatalogService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int number=Integer.parseInt(req.getParameter("number"));
        String itemId=req.getParameter("itemId");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.setQuantityByItemId(itemId, number);
        System.out.println("??");
        resp.setContentType("text/json");
        PrintWriter out =resp.getWriter();
        if (true) {
            out.println("{\"message\": \"success\"}");
        } else {
            out.println("{\"message\": \"error\"}");
        }

    }
}
