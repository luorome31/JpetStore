<c:if test="${!empty sessionScope.myList}">
  <hr>
  <p>Pet Favorites <br />
    Shop for more of your favorite pets here.</p>
  <ul class="list-inline table-bordered text-warning">
    <c:forEach var="product" items="${sessionScope.myList}">
      <li>
        <a href="productForm?productId=${product.productId}"><span class="glyphicon glyphicon-heart-empty"></span> ${product.name}</a>
        (${product.productId})</li>
    </c:forEach>
  </ul>

</c:if>