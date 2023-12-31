<%--
  Created by IntelliJ IDEA.
  User: 罗嘉烨
  Date: 2023/12/31
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp" %>

<%--<button id="button1">Information FillOut</button>--%>
<%--<button id="button2">Information Confirm</button>--%>
<%--<button id="button3">Your Order</button>--%>
<div id="stageShow">
    <div class="showBlock">
        <div class="icon2" style="color: blue">Fill Out</div>
        <div class="next"></div>
        <div class="pre">
            <div class="preup"></div>

            <div class="predown"></div>
        </div>
    </div>
    <div class="showBlock">
        <div class="icon2">Confirm</div>
        <div class="next"></div>
        <div class="pre">
            <div class="preup"></div>
            <div class="predown"></div>
        </div>
    </div>
    <div class="showBlock">
        <div class="icon2">Your Order</div>
    </div>


</div>


<div id="div1" class="Catalog">
    <table>
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
                <input type="checkbox" name="shippingAddressRequired"/>
                Ship to different address...
            </td>
        </tr>
    </table>
    <%--        <input class="confirmButton" type="submit" value="Continue" />--%>
    <button class="confirmButton">confirm</button>

</div>
<div id="div2" class="Catalog" hidden>
    <table>
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
    <button class="confirmButton">confirm</button>
</div>
<div id="div3" class="Catalog" hidden>
    <table>
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
                <table>
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

<%@ include file="../common/bottom.jsp" %>
