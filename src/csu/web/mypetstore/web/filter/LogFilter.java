package csu.web.mypetstore.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.LogService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogFilter implements Filter {
    private LogService logService = new LogService();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        String username = loginAccount == null ? "" : loginAccount.getUsername();
        String requestURI = httpRequest.getRequestURI();
        String operation = "";

        // 根据请求的Servlet和参数来设置操作信息
        if (requestURI.contains("/itemForm")) {
            String itemId = httpRequest.getParameter("itemId");
            operation = "View Item - itemId: " + itemId;
        } else if (requestURI.contains("/addItemToCart")) {
            String workingItemId = httpRequest.getParameter("workingItemId");
            operation = "Add Item to Cart - workingItemId: " + workingItemId;
        } else if (requestURI.contains("/removeCartItem")) {
            String workingItemId = httpRequest.getParameter("workingItemId");
            operation = "Remove Item from Cart - workingItemId: " + workingItemId;
        }

        // 记录用户行为
        if (!username.isEmpty() && !operation.isEmpty()) {
            logService.addRecord(username, operation);
        }

        // 继续处理请求
        chain.doFilter(request, response);
    }
}
