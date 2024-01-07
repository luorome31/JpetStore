<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="saveAccount" method="post">
        <h3>User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td><input type="text" name="username" id="username" placeholder="${sessionScope.loginAccount.username}"></td>
                <div id="feedback"></div>

            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="password" autofocus="autofocus"/></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword" /></td>
            </tr>
        </table>
        <%@ include file="IncludeAccountFields.jsp"%>
        <input type="submit" name="editAccount" value="Save Account Information" />

    </form>
    <!--event="listOrders-->
    <a href="listOrders">My Orders</a>
    <br>
    <a href="searchRecord">My Record</a>
    <script src="js/register.js"></script>

</div>

<%@ include file="../common/bottom.jsp"%>
