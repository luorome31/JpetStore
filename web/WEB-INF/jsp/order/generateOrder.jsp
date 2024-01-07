
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>

<html>

<head>
    <title>MyPetStore</title>
    <meta charset="UTF-8">
    <link rel="StyleSheet" href="bootstrap/css/bootstrap.css" type="text/css" media="screen" />
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
<div class="container">
    <ul class="col-md-8 nav nav-tabs nav-justified">
        <li></li>
        <li class="nav-item" id="tab1">
            <a class="nav-link active" href="#div1" data-toggle="tab">Shipping & Billing</a>
        </li>
        <li class="nav-item " id="tab2">
            <a class="nav-link" href="#div2" data-toggle="tab">Confirm Order</a>
        </li>
        <li class="nav-item" id="tab3">
            <a class="nav-link" href="#div3" data-toggle="tab">Confirm</a>
        </li>
        <li></li>
    </ul>
    <div class="tab-content myTab">
        <div id="div1" class="tab-pane in active Catalog col-md-8 col-md-offset-2 ">
            <table class="table table-bordered table-striped table-hover">
                <tr>
                    <th colspan="2">Payment Details</th>
                </tr>
                <tr>
                    <td>Card Type:</td>
                    <td>
                        <select name="order.cardType">
                            <c:forEach items="${sessionScope.cardList}" var="cardType">
                                <option value="${cardType}">${cardType}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Card Number:</td>
                    <td>
                        <input type="text" value=${sessionScope.order.creditCard}/> * Use a fake number!
                    </td>
                </tr>
                <tr>
                    <td>Expiry Date (MM/YYYY):</td>
                    <td>
                        <input type="text" value=${sessionScope.order.expiryDate}/>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">Billing Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td>
                        <input type="text" value=${sessionScope.order.billToFirstName}/>
                    </td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td>
                        <input type="text" value=${sessionScope.order.billToLastName}/>
                    </td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td>
                        <input type="text" value=${sessionScope.order.billAddress1}/>
                    </td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td>
                        <input type="text" value=${sessionScope.order.billAddress2}/>
                    </td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>
                        <input type="text" value=${sessionScope.order.billCity}/>
                    </td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td>
                        <input type="text" size="4" value=${sessionScope.order.billState}/>
                    </td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td>
                        <input type="text" size="10" value=${sessionScope.order.billZip}/>
                    </td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td>
                        <input type="text" size="15" value=${sessionScope.order.billCountry}/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox"  name="shippingAddressRequired" value="true" id="shippingAddress"/>
                        Ship to the same address
                    </td>

                </tr>
            </table>
            <%--        <input class="confirmButton" type="submit" value="Continue" />--%>
            <button class="confirmButton btn-primary col-md-offset-5" id="fillOutBtn">confirm</button>

        </div>
        <div id="div2" class="tab-pane fade Catalog col-md-8 col-md-offset-2 " hidden>
            <table class="table table-bordered table-striped table-hover">
                <tr>
                    <th align="center" colspan="2"><font size="4"><b>Order</b></font><br/>
                        <%--				<font size="3"><b> <%= new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(request.getAttribute("orderDate")) %></b></font>--%>
                    </th>
                </tr>
                <tr>
                    <th colspan="2">Billing Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><c:out value="${sessionScope.order.billToFirstName}"/></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><c:out value="${sessionScope.order.billToLastName}"/></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><c:out value="${sessionScope.order.billAddress1}"/></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><c:out value="${sessionScope.order.billAddress2}"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><c:out value="${sessionScope.order.billCity}"/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><c:out value="${sessionScope.order.billState}"/></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><c:out value="${sessionScope.order.billZip}"/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><c:out value="${sessionScope.order.billCountry}"/></td>
                </tr>
                <tr>
                    <th colspan="2">Shipping Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><c:out value="${sessionScope.order.shipToFirstName}"/></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><c:out value="${sessionScope.order.shipToLastName}"/></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><c:out value="${sessionScope.order.shipAddress1}"/></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><c:out value="${sessionScope.order.shipAddress2}"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><c:out value="${sessionScope.order.shipCity}"/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><c:out value="${sessionScope.order.shipState}"/></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><c:out value="${sessionScope.order.shipZip}"/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><c:out value="${sessionScope.order.shipCountry}"/></td>
                </tr>
            </table>

            <input type="hidden" name="confirmed" value="true">
            <button class="confirmButton btn-primary col-md-offset-5" id="confirmBtn">confirm</button>
        </div>
        <div id="div3" class="tab-pane fade Catalog col-md-8 col-md-offset-2" hidden>
            <table class="table table-bordered table-striped table-hover">
                <tr>
                    <th align="center" colspan="2">Order #<c:out value="${sessionScope.order.orderId}"/></th>
                </tr>
                <tr>
                    <th colspan="2">Payment Details</th>
                </tr>
                <tr>
                    <td>Card Type:</td>
                    <td><c:out value="${sessionScope.order.cardType}"/></td>
                </tr>
                <tr>
                    <td>Card Number:</td>
                    <td><c:out value="${sessionScope.order.creditCard}"/> * Fake number!</td>
                </tr>
                <tr>
                    <td>Expiry Date (MM/YYYY):</td>
                    <td><c:out value="${sessionScope.order.expiryDate}"/></td>
                </tr>
                <tr>
                    <th colspan="2">Billing Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><c:out value="${sessionScope.order.billToFirstName}"/></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><c:out value="${sessionScope.order.billToLastName}"/></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><c:out value="${sessionScope.order.billAddress1}"/></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><c:out value="${sessionScope.order.billAddress2}"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><c:out value="${sessionScope.order.billCity}"/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><c:out value="${sessionScope.order.billState}"/></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><c:out value="${sessionScope.order.billZip}"/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><c:out value="${sessionScope.order.billCountry}"/></td>
                </tr>
                <tr>
                    <th colspan="2">Shipping Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><c:out value="${sessionScope.order.shipToFirstName}"/></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><c:out value="${sessionScope.order.shipToLastName}"/></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><c:out value="${sessionScope.order.shipAddress1}"/></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><c:out value="${sessionScope.order.shipAddress2}"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><c:out value="${sessionScope.order.shipCity}"/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><c:out value="${sessionScope.order.shipState}"/></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><c:out value="${sessionScope.order.shipZip}"/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><c:out value="${sessionScope.order.shipCountry}"/></td>
                </tr>
                <tr>
                    <td>Courier:</td>
                    <td><c:out value="${sessionScope.order.courier}"/></td>
                </tr>
                <tr>
                    <td colspan="2">Status: <c:out value="${sessionScope.order.status}"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table class="table">
                            <tr>
                                <th>Item ID</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total Cost</th>
                            </tr>
                            <c:forEach var="lineItem" items="${sessionScope.order.lineItems}">
                                <tr>
                                    <td>
                                        <a href="<%= request.getContextPath() %>/itemForm?itemId=${lineItem.item.itemId}">${lineItem.item.itemId}</a>
                                    </td>
                                    <td><c:if test="${lineItem.item != null}">
                                        ${lineItem.item.attribute1}
                                        ${lineItem.item.attribute2}
                                        ${lineItem.item.attribute3}
                                        ${lineItem.item.attribute4}
                                        ${lineItem.item.attribute5}
                                        ${lineItem.item.product.name}
                                    </c:if> <c:if test="${lineItem.item == null}">
                                        <i>{description unavailable}</i>
                                    </c:if></td>
                                    <td>${lineItem.quantity}</td>
                                    <td><fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00"/></td>
                                    <td><fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00"/></td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <th colspan="5">Total: <fmt:formatNumber value="${sessionScope.order.totalPrice}"
                                                                         pattern="$#,##0.00"/></th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </div>


</div>
<div class="modal fade" id="shippingCheck" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title text-capitalize" id="ConfirmHead" >fill out your shipping address</h4>
            </div>
            <div class="modal-body">
                <div>
                    <table class="table table-striped table-bordered text-center table-hover col-md-8">
                        <tr>
                            <th colspan="2">Shipping Address</th>
                        </tr>
                        <tr>
                            <td>First name:</td>
                            <td><input type="text" value=${sessionScope.order.shipToFirstName} /></td>
                        </tr>
                        <tr>
                            <td>Last name:</td>
                            <td><input type="text" value=${sessionScope.order.shipToLastName} /></td>
                        </tr>
                        <tr>
                            <td>Address 1:</td>
                            <td><input type="text" size="40" value=${sessionScope.order.shipAddress1} /></td>
                        </tr>
                        <tr>
                            <td>Address 2:</td>
                            <td><input type="text" size="40" value=${sessionScope.order.shipAddress2} /></td>
                        </tr>
                        <tr>
                            <td>City:</td>
                            <td><input type="text" value=${sessionScope.order.shipCity} /></td>
                        </tr>
                        <tr>
                            <td>State:</td>
                            <td><input type="text" size="4" value=${sessionScope.order.shipState} /></td>
                        </tr>
                        <tr>
                            <td>Zip:</td>
                            <td><input type="text" size="10" value=${sessionScope.order.shipZip} /></td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td><input type="text" size="15" value=${sessionScope.order.shipCountry} /></td>
                        </tr>
                    </table>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="closeBtn">close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="submitBtn">submit</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
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
<script src="js/generateOrder.js"></script>
<script src="js/setInitMainList.js"></script>
<script src="js/preView.js"></script>
</body>
</html>

