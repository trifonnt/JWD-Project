<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${errorMessage != null}">
	<div>
		<label for="errorMessage">Error Message</label> <input id="errorMessage" type="text" name="errorMessage" value="${errorMessage}" readonly="readonly"/>
	</div>
</c:if>

<c:if test="${productType != null && productType.id > 0 }">
	<c:set var="formUrl" scope="request" value="${pageContext.request.contextPath}/product-type/${productType.id}"/>
</c:if>
<c:if test="${productType == null || productType.id <= 0 }">
	<c:set var="formUrl" scope="request" value="${pageContext.request.contextPath}/product-type"/>
</c:if>

<form:form class="semantic" method="POST" action="${formUrl}" modelAttribute="productType">
	<fieldset>
		<c:if test="${productType != null && productType.id > 0 }">
			<legend>Edit Product Type</legend>
		</c:if>
		<c:if test="${productType == null || productType.id <= 0 }">
			<legend>Create new Product Type</legend>
		</c:if>

		<div>
			<label for="name">Product Type name</label> <input id="name" type="text" name="name" value="${productType.name}" />
		</div>
	</fieldset>
	<c:if test="${productType != null && productType.id > 0 }">
		<input id="id" type="text" name="id" value="${productType.id}" readonly="readonly" hidden=""/>
	</c:if>

	<div class="button-row">
		<a href="${pageContext.request.contextPath}/product-type-register">Cancel</a> or <input type="submit" value="Submit" />
	</div>
</form:form>