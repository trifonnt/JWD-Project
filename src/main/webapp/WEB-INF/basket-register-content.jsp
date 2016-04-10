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

<form:form class="semantic" method="GET" action="${pageContext.request.contextPath}/basket-register" modelAttribute="basketSearch">
	<fieldset>
		<legend>Search Criteria</legend>
		<table>
			<tr>
				<td><label for="productNumber">Product Number</label></td>
				<td><input id="productNumber" name="productNumber"/></td>
				<td><label for="productName">Product Name</label></td>
				<td><input id="productName" name="productName" autofocus/></td>
				<td><label for="description">Product Description</label></td>
				<td><input id="description" name="description"/></td>
				<td><label for="productTypeName">Product Type</label></td>
				<td><input id="productTypeName" name="productTypeName"/></td>

				<td><label for="qty">Qty.</label></td>
				<td><input id="qty" name="qty"/></td>
				<td><label for="price">Price</label></td>
				<td><input id="price" name="price"/></td>
				<td><label for="totalNetAmt">Line Total</label></td>
				<td><input id="totalNetAmt" name="totalNetAmt"/></td>

			</tr>
		</table>
	</fieldset>
	<button id="search">Search</button>
</form:form>

<table>
	<thead>
		<tr><th>Line Number</th><th>Prod. Number</th><th>Name</th><th>Description</th><th>Type</th><th>Qty.</th><th>Price</th><th>Line Total</th><th>Operation</th><tr>
	</thead>
	<tbody>
		<c:forEach var="orderLineDTO" items="${orderLineDTOs}">
			<tr>
				<td>${orderLineDTO.orderLineNumber}</td>
				<td>${orderLineDTO.productNumber}</td>
				<td>${orderLineDTO.productName}</td>
				<td>${orderLineDTO.description}</td>
				<td>${orderLineDTO.productTypeName}</td>
				<td>${orderLineDTO.qty}</td>
				<td>${orderLineDTO.price}</td>
				<td>${orderLineDTO.totalNetAmt}</td>
				<td>
					<form:form class="semantic" method="POST" action="order/${orderLineDTO.orderId}/line/${orderLineDTO.orderLineId}"><button id="update">Update Line</button></form:form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>