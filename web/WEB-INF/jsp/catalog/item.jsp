
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>

<html>

<head>
    <title>MyPetStore</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="StyleSheet" href="css/mainForm.css" type="text/css" media="screen" />

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
<div id="Catalog" class="container">
    <div class="col-md-4 col-md-offset-4">
        <table class="table table-bordered table-striped ">
            <tr>
                <td>${sessionScope.product.description}</td>
            </tr>
            <tr>
                <td><b>${sessionScope.item.itemId}</b></td>
            </tr>
            <tr>
                <td>
                    <b><font size="4">
                        ${sessionScope.item.attribute1}
                        ${sessionScope.item.attribute2}
                        ${sessionScope.item.attribute3}
                        ${sessionScope.item.attribute4}
                        ${sessionScope.item.attribute5}
                        ${sessionScope.product.name}
                    </font></b>
                </td>
            </tr>
            <tr>
                <td>${sessionScope.product.name}</td>
            </tr>
            <tr>
                <td>
                    <c:if test="${sessionScope.item.quantity <= 0}">
                        Back ordered.
                    </c:if>
                    <c:if test="${sessionScope.item.quantity > 0}">
                        ${sessionScope.item.quantity} in stock.
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><fmt:formatNumber value="${sessionScope.item.listPrice}" pattern="$#,##0.00" /></td>
            </tr>
            <tr>
                <td>
                    <form action="addItemToCart" method="POST">
                        <input type="hidden" name="flag" id="flag" value="${sessionScope.flag+1}">
                        <input type="hidden" name="workingItemId" value="${sessionScope.item.itemId}">
                        <button type="submit" class="Button">Add to Cart</button>
                    </form>
                </td>
            </tr>
        </table>

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
    <div class="product-preview"></div>

</div>

<script src="js/productAuto.js"></script>
<script src="js/cartNumber.js"></script>
<script src="js/orderConfirm.js"></script>
<script src="js/setInitMainList.js"></script>
<script src="js/preView.js"></script>
</body>
</html>

