package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.LogDao;
import csu.web.mypetstore.persistence.impl.LogDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import csu.web.mypetstore.domain.Record;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

public class searchRecordServlet extends HttpServlet {
    private AccountService accountService = new AccountService();
    private static final String SEARCH_RECORD_FORM = "/WEB-INF/jsp/account/searchRecord.jsp";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("loginAccount");
        String username = account.getUsername();
        List<Record> recordList = accountService.searchRecord(username);
        request.setAttribute("recordList", recordList);
        request.getRequestDispatcher(SEARCH_RECORD_FORM).forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
