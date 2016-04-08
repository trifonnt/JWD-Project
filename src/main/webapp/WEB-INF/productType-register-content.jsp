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
	<a href="product-register">Manage <b>Products</b></a> | 
	<a href="customer-register">Manage <b>Customers</b></a> |  
</sec:authorize>
<a href="logout">Log Out</a>
<hr/>

<form:form class="semantic" method="GET" action="${pageContext.request.contextPath}/product-type-register" modelAttribute="productTypeSearch">
	<fieldset>
		<legend>Search Criteria</legend>
		<table>
			<tr>
				<td><label for="name">Product Type Name</label></td>
				<td><input id="name" name="name" autofocus/></td>
			</tr>
		</table>
	</fieldset>
	<button id="search">Search</button>
</form:form>

<table>
	<thead>
		<tr><th>Id</th><th>Name</th><th>Description</th><th>Created By</th><th>Operation</th><tr>
	</thead>
	<tbody>
		<c:forEach var="productTypeDTO" items="${productTypeDTOs}">
			<tr>
				<td>${productTypeDTO.id}</td>
				<td>${productTypeDTO.name}</td>
				<td>${productTypeDTO.description}</td>
				<td>${productTypeDTO.creatorName}</td>
				<td><a href="product-type/${productTypeDTO.id}/edit">Edit</a>
<!--			| <a href="product-type/${productTypeDTO.id}/de-activate">De-Activate</a-->
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>