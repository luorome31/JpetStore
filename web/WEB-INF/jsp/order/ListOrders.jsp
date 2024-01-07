
<%@ include file="../common/newTop.jsp"%>

<h2>My Orders</h2>

<table class="table table-striped text-center">
	<tr>
		<th class="text-center">Order ID</th>
		<th class="text-center">Date</th>
		<th class="text-center">Total Price</th>
	</tr>

	<c:forEach var="order" items="${sessionScope.orderList}">
		<tr>
			<td><a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a></td>
			<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></td>
			<td><fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" /></td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../common/bottom.jsp"%>


