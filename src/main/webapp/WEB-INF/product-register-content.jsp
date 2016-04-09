<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${errorMessage != null}">
	<div>
		<label for="errorMessage">Error Message</label> <input type="text" name="errorMessage" id="errorMessage" value="${errorMessage}" readonly="readonly"/>
	</div>
</c:if>

<sec:authorize access="hasAnyRole('ROLE_SHOP_EMPLOYEE')">
	<a href="product-type-register">Manage <b>Product Types</b></a> | 
	<a href="customer-register">Manage <b>Customers</b></a> |  
</sec:authorize>
<a href="logout">Log Out</a>
<br/>
<sec:authorize access="hasAnyRole('ROLE_SHOP_EMPLOYEE')">
	<c:forEach var="productType" items="${productTypeDTOs}">
		<a href="product/new/${productType.id}">Create <b>${productType.name}</b></a> | 
	</c:forEach>
</sec:authorize>
<hr/>

<form:form class="semantic" method="GET" action="${pageContext.request.contextPath}/product-register" modelAttribute="productSearch">
	<fieldset>
		<legend>Search Criteria</legend>
		<table>
			<tr>
				<td><label for="productNumber">Product Number</label></td>
				<td><input id="productNumber" name="productNumber"/></td>
				<td><label for="name">Product Name</label></td>
				<td><input id="name" name="name" autofocus/></td>
				<td><label for="description">Product Description</label></td>
				<td><input id="description" name="description"/></td>
				<td><label for="typeName">Product Type</label></td>
				<td><input id="typeName" name="typeName"/></td>
			</tr>
		</table>
	</fieldset>
	<button id="search">Search</button>
</form:form>

<table>
	<thead>
		<tr><th>Number</th><th>Name</th><th>Description</th><th>Type</th><th>Price</th><th>Qty. On Hand</th><th>Created By</th><th>Operation</th><tr>
	</thead>
	<tbody>
		<c:forEach var="productDTO" items="${productDTOs}">
			<tr>
				<td>${productDTO.productNumber}</td>
				<td>${productDTO.name}</td>
				<td>${productDTO.description}</td>
				<td><b>${productDTO.typeName}</b></td>
				<td>${productDTO.price}</td>
				<td>${productDTO.qtyOnHand}</td>
				<td>${productDTO.creatorName}</td>
				<td>
					<sec:authorize access="hasAnyRole('ROLE_SHOP_EMPLOYEE')">
						 <a href="product/${productDTO.id}/edit">Edit</a> | 
<!--				 <a href="product/${productDTO.id}/de-activate">De-Activate</a> | -->
					</sec:authorize>
						<!--a href="product/${productDTO.id}/order">Add to Basket</a-->
						<form:form class="semantic" method="POST" action="product/${productDTO.id}/order"><button id="addToBasket">Basket</button></form:form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>