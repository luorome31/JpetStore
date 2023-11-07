package csu.web.mypetstore.web.servlet;


import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveAccountServlet extends HttpServlet {

    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/editAccountForm.jsp";

    private Account loginAccount;
    private AccountService accountService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loginAccount = (Account) session.getAttribute("loginAccount");

        String username = loginAccount.getUsername();
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");
        String languagePreference = request.getParameter("languagePreference");
        String favouriteCategoryId = request.getParameter("favouriteCategoryId");
        String listOption = request.getParameter("listOption");
        String bannerOption = request.getParameter("bannerOption");

        loginAccount.setUsername(username);
        loginAccount.setPassword(password);
        loginAccount.setFirstName(firstName);
        loginAccount.setLastName(lastName);
        loginAccount.setEmail(email);
        loginAccount.setPhone(phone);
        loginAccount.setAddress1(address1);
        loginAccount.setAddress2(address2);
        loginAccount.setCity(city);
        loginAccount.setState(state);
        loginAccount.setZip(zip);
        loginAccount.setCountry(country);
        loginAccount.setLanguagePreference(languagePreference);
        loginAccount.setFavouriteCategoryId(favouriteCategoryId);
        loginAccount.setListOption(Boolean.parseBoolean(listOption));
        loginAccount.setBannerOption(Boolean.parseBoolean(bannerOption));

        accountService = new AccountService();
        accountService.updateAccount(loginAccount);

        session.setAttribute("loginAccount", loginAccount);


        request.getRequestDispatcher(EDIT_ACCOUNT).forward(request, response);
    }
}


