package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainListServlet extends HttpServlet {
    CatalogService catalogService = new CatalogService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("categoryId");
        System.out.println(category);
        List<Item> itemList = catalogService.getMainListByCategory(category);
        //遍历每一个item，获取其对应的product，修改其product的description，使之只保留<>及其之间的内容
        for(Item item : itemList){
            String description = item.getProduct().getDescription();
            int start = description.indexOf("<");
            int end = description.indexOf(">");
            String newDescription = description.substring(start,end+1);
//            String modifiedString = newDescription.replaceFirst("(?i)<image\\s+", "<image class=\"productImage\" ");
            item.getProduct().setDescription(newDescription);
        }
//        System.out.println("success");
//        HttpSession session = req.getSession();
//        session.setAttribute("mainList", itemList);
        //将itemList转换为json格式返回给前端
        JSONArray mainListArray = new JSONArray();
        for (Item item : itemList) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("itemId", item.getItemId());
            itemObject.put("listPrice", item.getListPrice());
            itemObject.put("description", item.getProduct().getDescription());

            mainListArray.add(itemObject);
        }

        JSONObject mainJsonObject = new JSONObject();
        mainJsonObject.put("mainList", mainListArray);

        String jsonString = mainJsonObject.toString();
        System.out.println(jsonString);
        resp.getWriter().write(jsonString);
    }
}
