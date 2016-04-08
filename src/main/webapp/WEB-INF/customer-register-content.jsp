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
<br/>
<sec:authorize access="hasAnyRole('ROLE_SHOP_EMPLOYEE')">
		<a href="customer/new">Register <b>Customer</b></a>
</sec:authorize>
<hr/>

<form:form class="semantic" method="GET" action="${pageContext.request.contextPath}/customer-register" modelAttribute="customerSearch">
	<fieldset>
		<legend>Search Criteria</legend>
		<table>
			<tr>
				<td><label for="id">Customer Id</label></td>
				<td><input id="id" name="id" value="0"/></td>
				<td><label for="username">Username</label></td>
				<td><input id="username" name="username" autofocus/></td>
			</tr>
		</table>
	</fieldset>
	<button id="search">Search</button>
</form:form>

<table>
	<thead>
		<tr><th>Id</th><th>Username</th><th>Email</th><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Created By</th><th>Roles</th><th>Operation</th><tr>
	</thead>
	<tbody>
		<c:forEach var="customerDTO" items="${customerDTOs}">
			<tr>
				<td>${customerDTO.id}</td>
				<td>${customerDTO.username}</td>
				<td>${customerDTO.email}</td>
				<td>${customerDTO.firstName}</td>
				<td>${customerDTO.middleName}</td>
				<td>${customerDTO.lastName}</td>
				<td>${customerDTO.creatorName}</td>
				<td>${customerDTO.roles}</td>
				<td><a href="customer/${customerDTO.id}/edit">Edit</a>
<!--			| <a href="customer/${customerDTO.id}/de-activate">De-Activate</a-->
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>