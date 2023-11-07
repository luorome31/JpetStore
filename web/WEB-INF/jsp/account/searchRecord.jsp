<%--
  Created by IntelliJ IDEA.
  User: 罗嘉烨
  Date: 2023/11/7
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp" %>

<h2>My Logs</h2>

<table>
    <tr>
        <th><b>User Name</b></th>
        <th><b>Behavior</b></th>
        <th><b>Date</b></th>
    </tr>

    <c:forEach items="${requestScope.recordList}" var="record">
        <tr>
            <td><c:out value="${record.getUsername()}"/></td>
            <td><c:out value="${record.getRecord()}"/></td>
            <td><c:out value="${record.getRecordDate()}"/></td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../common/bottom.jsp" %>
