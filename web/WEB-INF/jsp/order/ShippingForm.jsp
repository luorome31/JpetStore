<%@ include file="../common/top.jsp"%>
<div id="Catalog">
    <form action="newOrder" method="post">
        <table>
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
        <input type="hidden" name="shippingAddressRequired" value="true">
        <input type="submit" class="Button" value="confirm">
    </form>
</div>
<%@ include file="../common/bottom.jsp"%>
