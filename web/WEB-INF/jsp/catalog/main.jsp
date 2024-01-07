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
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="StyleSheet" href="css/mypetstore.css" type="text/css" media="screen" />

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>


</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="mainForm"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="cartForm"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
            <img align="middle" src="images/separator.gif" />
            <c:if test="${sessionScope.loginAccount == null}">
                <a href="signonForm">Sign In</a>
                <img align="middle" src="images/separator.gif" />
            </c:if>

            <c:if test="${sessionScope.loginAccount != null}">
                <a href="signOut">Sign Out</a>
                <img align="middle" src="images/separator.gif" />
                <a href="editAccount"> My Account</a>
                <img align="middle" src="images/separator.gif" />
            </c:if>
            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search" >
        <div id="SearchContent">
            <form action="searchProduct" method="post" autocomplete="off">
                <input  type="text" name="keyword"  id="keyword" placeholder="Search" aria-label="Search">

                <input  type="submit" name="searchProducts" value="Search">
            </form>
        </div>
        <div id="productAutoComplete">
            <ul id="productAutoList">
                <%--                        <li class="productAutoItem">Amazon</li>--%>
                <%--                        <li class="productAutoItem">Amazon</li>--%>
                <%--                        <li class="productAutoItem">Amazon</li>--%>
                <%--                        <li class="productAutoItem">Amazon</li>--%>
                <%--                        <li class="productAutoItem">Amazon</li>--%>
                <%--                        <li class="productAutoItem">Amazon</li>--%>
            </ul>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="categoryForm?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
        <img src="images/separator.gif" />
        <a href="categoryForm?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
    </div>

</div>
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
        <div class="row">


            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-4" class="productImage">
                        <image src="images/fish3.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-4</p>
                        <p class="card-text">price: $18.50</p>
                    </div>
                </div>
            </div>





            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-5" class="productImage">
                        <image src="images/fish3.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-5</p>
                        <p class="card-text">price: $18.50</p>
                    </div>
                </div>
            </div>





            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-20" class="productImage">
                        <image src="images/fish2.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-20</p>
                        <p class="card-text">price: $5.50</p>
                    </div>
                </div>
            </div>





            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-21" class="productImage">
                        <image src="images/fish2.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-21</p>
                        <p class="card-text">price: $5.29</p>
                    </div>
                </div>
            </div>


        </div>



        <div class="row">


            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-1" class="productImage">
                        <image src="images/fish1.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-1</p>
                        <p class="card-text">price: $16.50</p>
                    </div>
                </div>
            </div>





            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-2" class="productImage">
                        <image src="images/fish1.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-2</p>
                        <p class="card-text">price: $16.50</p>
                    </div>
                </div>
            </div>





            <div class="col-md-3">
                <div class="card border-success mb-3">
                    <a href="itemForm?itemId=EST-3" class="productImage">
                        <image src="images/fish4.gif">
                    </a>
                    <div class="card-body">
                        <p class="card-text">EST-3</p>
                        <p class="card-text">price: $18.50</p>
                    </div>
                </div>
            </div>


        </div>
    </div>


</div>

<div id="Footer">

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

