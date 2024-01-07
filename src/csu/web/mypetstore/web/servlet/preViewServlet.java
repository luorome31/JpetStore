package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpsServer;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class preViewServlet extends HttpServlet {
    CatalogService catalogService = new CatalogService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        Item item = catalogService.getItem(itemId);
        System.out.println("itmeId:"+itemId);
        JSONObject itemObject = new JSONObject();
        System.out.println(item);
        itemObject.put("description",item.getProduct().getDescription());
        itemObject.put("name",item.getProduct().getName());
        itemObject.put("quantity",item.getQuantity());
        String jsonString = itemObject.toString();
        resp.getWriter().write(jsonString);
    }
}
