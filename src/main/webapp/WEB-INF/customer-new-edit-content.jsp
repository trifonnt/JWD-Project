<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${errorMessage != null}">
	<div>
		<label for="errorMessage">Error Message</label> <input id="errorMessage" type="text" name="errorMessage" value="${errorMessage}" readonly="readonly"/>
	</div>
</c:if>

<c:if test="${customer != null && customer.id > 0 }">
	<c:set var="formUrl" scope="request" value="${pageContext.request.contextPath}/customer/${customer.id}"/>
</c:if>
<c:if test="${customer == null || customer.id <= 0 }">
	<c:set var="formUrl" scope="request" value="${pageContext.request.contextPath}/customer"/>
</c:if>

<form:form class="semantic" method="POST" action="${formUrl}" modelAttribute="customer">
	<fieldset>
		<c:if test="${customer != null && customer.id > 0 }">
			<legend>Edit Customer</legend>
		</c:if>
		<c:if test="${customer == null || customer.id <= 0 }">
			<legend>Create new Customer</legend>
		</c:if>

		<div>
			<label for="username">Username</label> <input id="username" type="text" name="username" value="${customer.username}" />
		</div>
		<div>
			<label for="email">Email</label> <input id="email" type="text" name="email" value="${customer.email}" />
		</div>
		<div>
			<label for="firstName">First Name</label> <input id="firstName" type="text" name="firstName" value="${customer.firstName}" />
		</div>
		<div>
			<label for="middleName">Middle Name</label> <input id="middleName" type="text" name="middleName" value="${customer.middleName}" />
		</div>
		<div>
			<label for="lastName">Last Name</label> <input id="lastName" type="text" name="lastName" value="${customer.lastName}" />
		</div>
	</fieldset>
	<c:if test="${customer != null && customer.id > 0 }">
		<input id="id" type="text" name="id" value="${customer.id}" readonly="readonly" hidden=""/>
	</c:if>

	<div class="button-row">
		<a href="${pageContext.request.contextPath}/customer-register">Cancel</a> or <input type="submit" value="Submit" />
	</div>
</form:form>