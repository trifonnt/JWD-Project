<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${errorMessage != null}">
	<div>
		<label for="errorMessage">Error Message</label> <input id="errorMessage" type="text" name="errorMessage" value="${errorMessage}" readonly="readonly"/>
	</div>
</c:if>

<c:if test="${product != null && product.id > 0 }">
	<c:set var="formUrl" scope="request" value="${pageContext.request.contextPath}/product/${product.id}"/>
</c:if>
<c:if test="${product == null || product.id <= 0 }">
	<c:set var="formUrl" scope="request" value="${pageContext.request.contextPath}/product"/>
</c:if>

<form:form class="semantic" method="POST" action="${formUrl}" modelAttribute="product">
	<fieldset>
		<c:if test="${product != null && product.id > 0 }">
			<legend>Edit Product</legend>
		</c:if>
		<c:if test="${product == null || product.id <= 0 }">
			<legend>Create new Product</legend>
		</c:if>

		<div>
			<label for="productNumber">Product number</label> <input id="productNumber" type="text" name="productNumber" value="${product.productNumber}" />
		</div>
		<div>
			<label for="name">Product name</label> <input id="name" type="text" name="name" value="${product.name}" />
		</div>
		<div>
			<label for="description">Description</label> <input id="description" type="text" name="description" value="${product.description}" />
		</div>
		<div>
			<label for="price">Price</label> <input id="price" name="price" class="money" value="${product.price}"/>
		</div>
		<div>
			<label for="qtyOnHand">Qty. On Hand</label> <input id="qtyOnHand" name="qtyOnHand" class="money" value="${product.qtyOnHand}"/>
		</div>
	</fieldset>
	<c:if test="${product != null && product.id > 0 }">
		<input id="id" type="text" name="id" value="${product.id}" readonly="readonly" hidden=""/>
	</c:if>
	<c:if test="${product == null || product.id <= 0 }">
		<input id="typeName" type="text" name="typeName" value="${productTypeName}" readonly="readonly" hidden=""/>
	</c:if>

	<div class="button-row">
		<a href="${pageContext.request.contextPath}/product-register">Cancel</a> or <input type="submit" value="Submit" />
	</div>
</form:form>