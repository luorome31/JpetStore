<%--
  Created by IntelliJ IDEA.
  User: 罗嘉烨
  Date: 2023/11/7
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>


<div id="Catalog">
	<table>
		<tr>
			<th align="center" colspan="2"><font size="4"><b>Order</b></font><br />
<%--				<font size="3"><b> <%= new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(request.getAttribute("orderDate")) %></b></font>--%>
			</th>
		</tr>
		<tr>
			<th colspan="2">Billing Address</th>
		</tr>
		<tr>
			<td>First name:</td>
			<td><c:out value="${sessionScope.order.billToFirstName}" /></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><c:out value="${sessionScope.order.billToLastName}" /></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><c:out value="${sessionScope.order.billAddress1}" /></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><c:out value="${sessionScope.order.billAddress2}" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><c:out value="${sessionScope.order.billCity}" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><c:out value="${sessionScope.order.billState}" /></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><c:out value="${sessionScope.order.billZip}" /></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><c:out value="${sessionScope.order.billCountry}" /></td>
		</tr>
		<tr>
			<th colspan="2">Shipping Address</th>
		</tr>
		<tr>
			<td>First name:</td>
			<td><c:out value="${sessionScope.order.shipToFirstName}" /></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><c:out value="${sessionScope.order.shipToLastName}" /></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><c:out value="${sessionScope.order.shipAddress1}" /></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><c:out value="${sessionScope.order.shipAddress2}" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><c:out value="${sessionScope.order.shipCity}" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><c:out value="${sessionScope.order.shipState}" /></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><c:out value="${sessionScope.order.shipZip}" /></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><c:out value="${sessionScope.order.shipCountry}" /></td>
		</tr>
	</table>

	<form action="newOrder" method="post">
		<input type="hidden" name="confirmed" value="true">
		<input type="submit" class="Button"  value="Confirm">
	</form>
</div>
<%@ include file="../common/bottom.jsp"%>