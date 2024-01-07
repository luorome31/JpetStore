
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
    <link rel="StyleSheet" href="css/cart.css" type="text/css" media="screen" />

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

    <div id="Cart">

        <h2>Shopping Cart</h2>
        <div id="informMessage" class="text-danger"></div>
        <form action="updateCart" method="post">
            <table class="table table-striped table-hover">
                <tr>
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <c:if test="${sessionScope.cart.numberOfItems == 0}">
                    <tr>
                        <td colspan="8"><b>Your cart is empty.</b></td>
                    </tr>
                </c:if>

                <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                    <tr>
                        <td>
                            <a id="itemIdRef" href="itemForm?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                        </td>
                        <td>${cartItem.item.product.productId}</td>
                        <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
                        <td>${cartItem.inStock}</td>
                        <td>
                            <input class="itemNumber" type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
                        </td>
                        <td class="singlePrice" id="${cartItem.item.itemId}"><fmt:formatNumber value="${cartItem.item.listPrice}"
                                                                                               pattern="$#,##0.00" /></td>
                        <td class="totalPrice" id="${cartItem.item.itemId}"><fmt:formatNumber value="${cartItem.total}"
                                                                                              pattern="$#,##0.00" /></td>
                        <td>
                            <a href="removeCartItem?workingItemId=${cartItem.item.itemId}" class="Button">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7" id="subTotal">
                        Sub Total: <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                    </td>
                    <td>
                        <input class="btn-warning" type="submit" value="Update Cart">
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>
        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <button class="Button" data-toggle="modal" data-target="#cartConfirm">go to check</button>
        </c:if>
    </div>

    <div id="MyList">
        <c:if test="${sessionScope.loginAccount != null}">
            <c:if test="${!empty sessionScope.loginAccount.listOption}">
                <%@ include file="includeMyList.jsp"%>
            </c:if>
        </c:if>
    </div>

    <div id="Separator">&nbsp;</div>


    <div class="modal fade" id="cartConfirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="ConfirmHead">submit your order</h4>
                </div>
                <div class="modal-body">
                    <div>
                        The total price of the items you purchased is <span id="ConfirmPrice"></span>.
                        <br>
                        Would you like to proceed with submitting the order?
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">close</button>
                    <button type="button" class="btn btn-primary" id="submitBtn" href="myOrder">submit</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
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
<script src="js/setInitMainList.js"></script>
<script src="js/preView.js"></script>
</body>
</html>

