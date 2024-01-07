<%--
  Created by IntelliJ IDEA.
  User: 罗嘉烨
  Date: 2023/1/2
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>

<html>

<head>
    <title>MyPetStore</title>
    <meta charset="UTF-8">
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">--%>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="StyleSheet" href="css/newCss.css" type="text/css" media="screen" />

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</head>

<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="mainStyle">JPetStore</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li class="nav-item">
                <a class="nav-link" href="cartForm">
                    <img align="middle" name="img_cart" src="images/cart.gif" />
                </a>
            </li>

            <c:if test="${sessionScope.loginAccount == null}">
                <li class="nav-item">
                    <a class="nav-link" href="signonForm">Sign In</a>
                </li>
            </c:if>

            <c:if test="${sessionScope.loginAccount != null}">
                <li class="nav-item">
                    <a class="nav-link" href="signOut">Sign Out</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="editAccount">My Account</a>
                </li>
            </c:if>

            <li class="nav-item">
                <a class="nav-link" href="help.html">?</a>
            </li>
        </ul>
        <form class="navbar-form navbar-right" role="search" action="searchProduct" method="post" autocomplete="off" id="Search">
            <input class="form-control mr-sm-2" type="text" name="keyword" size="16" id="keyword"
                   placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>

    </div>
</nav>
<div id="Content">