package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLType;
import java.util.List;

public class MainStyleServlet extends HttpServlet {

    private static final String MAIN_STYLE = "/WEB-INF/jsp/catalog/newMain.jsp";
    CatalogService catalogService = new CatalogService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int flag = 0;
        session.setAttribute("flag",flag);
        String category ="FISH";
        List<Item> itemList = catalogService.getMainListByCategory(category);
        for(Item item : itemList){
            String description = item.getProduct().getDescription();
            int start = description.indexOf("<");
            int end = description.indexOf(">");
            String newDescription = description.substring(start,end+1);
            item.getProduct().setDescription(newDescription);
        }
        session.setAttribute("mainList", itemList);
        //遍历打印itemList
        for (Item item:itemList){
            System.out.println(item.getProduct().getDescription());
        }
        req.getRequestDispatcher(MAIN_STYLE).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

}
