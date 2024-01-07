<%--
  Created by IntelliJ IDEA.
  User: 罗嘉烨
  Date: 2023/12/31
  Time: 20:09
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
<%--    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="StyleSheet" href="css/mainForm.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="bootstrap/css/bootstrap.css" type="text/css" media="screen" />
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>

</head>

<body>

<nav class="navbar container" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="mainStyle">JPetStore</a>
        </div>
        <ul class="nav navbar-nav">
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
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                   Pet Category <b class="caret"></b>
                </a>
                <ul class="dropdown-menu text-muted text-uppercase">
                    <li><a href="categoryForm?categoryId=FISH">Fish</a></li>
                    <li><a href="categoryForm?categoryId=BIRDS">Bird</a></li>
                    <li><a href="categoryForm?categoryId=DOGS">Dog</a></li>
                    <li><a href="categoryForm?categoryId=REPTILES">Reptile</a></li>
                    <li><a href="categoryForm?categoryId=CATS">Cat</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="help.html">?</a>
            </li>
        </ul>
        <div id="SearchPlace" class="navbar-right">
            <div >
                <form class="navbar-form " role="search" action="searchProduct" method="post" autocomplete="off" id="Search">
                    <input class="form-control mr-sm-2" type="text" name="keyword" size="16" id="keyword"
                           placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
            <div id="productAutoComplete">
                <ul id="productAutoList">
<%--                    <li class="productAutoItem">Amazon</li>--%>
<%--                    <li class="productAutoItem">Amazon</li>--%>
<%--                    <li class="productAutoItem">Amazon</li>--%>
<%--                    <li class="productAutoItem">Amazon</li>--%>
<%--                    <li class="productAutoItem">Amazon</li>--%>
<%--                    <li class="productAutoItem">Amazon</li>--%>
                </ul>
            </div>

        </div>

</nav>
<div class="container">
    <div class="btn-group btn-group-sm buttonGroup" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-success">FISH</button>
        <button type="button" class="btn btn-danger">BIRDS</button>
        <button type="button" class="btn btn-primary">DOGS</button>
        <button type="button" class="btn btn-info">REPTILES</button>
        <button type="button" class="btn btn-secondary" >CATS</button>
    </div>
    <hr>
    <div class="container mainShow">
        <c:forEach var="item" items="${sessionScope.mainList}" varStatus="loopStatus">
            <c:if test="${loopStatus.index % 4 == 0}">
                <div class="row">
            </c:if>

            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=${item.itemId}" class="productImage">
                        ${item.product.description}
                    </a>
                    <div class="card-body">
                        <p class="card-text">${item.itemId}</p>
                        <p class="card-text">price: $${item.listPrice}</p>
                    </div>
                </div>
            </div>

            <c:if test="${loopStatus.index % 4 == 3 or loopStatus.last}">
                </div>
            </c:if>
        </c:forEach>

    </div>


</div>

<div class="container" id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">

        <c:if test="${sessionScope.loginAccount != null }">
            <c:if test="${sessionScope.loginAccount.bannerOption}">
                ${sessionScope.loginAccount.bannerName}
            </c:if>
        </c:if>
    </div>
    <div class="product-preview bg-info text-success"></div>

</div>

<script src="js/productAuto.js"></script>
<script src="js/cartNumber.js"></script>
<script src="js/orderConfirm.js"></script>
<script src="js/preView.js"></script>
<script src="js/setInitMainList.js"></script>
</body>
</html>

