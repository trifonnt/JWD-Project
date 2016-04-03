<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form name="login" action="${pageContext.request.contextPath}/login" method="POST">
	<label>Username</label><input id="username" name="username" type="text"/>
	<label>Password</label><input id="password" name="password" type="password" />
<%--	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
	<input name="submit" type="submit" value="Login" />
</form>