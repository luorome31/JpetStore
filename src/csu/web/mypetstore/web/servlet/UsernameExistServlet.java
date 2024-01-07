package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        AccountService service=new AccountService();
        Account usernameList=service.getAccount(username);

        String result=JSON.toJSONString(usernameList);
        resp.setContentType("text/json");
        //判断usernameList是否为空
        if(usernameList==null){
            resp.getWriter().write("{\"result\":" + false + "}");
            return;
        }
        if (username.equals(usernameList.getUsername())){
            //传回给前端json数据：data:{"result":"true"}
            resp.getWriter().write("{\"result\":" + true + "}");
        }

    }
}
