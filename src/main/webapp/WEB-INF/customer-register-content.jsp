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
	<a href="product-type-register">Manage <b>Product Types</b></a> | 
</sec:authorize>
<a href="logout">Log Out</a>
<hr/>

<form:form class="semantic" method="GET" action="${pageContext.request.contextPath}/customer-register" modelAttribute="customerSearch">
	<fieldset>
		<legend>Search Criteria</legend>
		<table>
			<tr>
				<td><label for="customerNumber">Customer Number</label></td>
				<td><input id="customerNumber" name="customerNumber"/></td>
				<td><label for="name">Customer Name</label></td>
				<td><input id="name" name="name"/></td>
			</tr>
		</table>
	</fieldset>
	<button id="search">Search</button>
</form:form>

<table>
	<thead>
		<tr><th>Number</th><th>Name</th><th>Created By</th><th>Operation</th><tr>
	</thead>
	<tbody>
		<c:forEach var="customerDTO" items="${customerDTOs}">
			<tr>
				<td>${customerDTO.id}</td>
				<td>${customerDTO.username}</td>
				<td>${customerDTO.creatorName}</td>
				<td><a href="customer/${customerDTO.id}/edit">Edit</a>
					<br/><a href="customer/${customerDTO.id}/de-activate">De-Activate</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>