<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${errorMessage != null}">
	<div>
		<label for="errorMessage">Error Message</label> <input id="errorMessage" type="text" name="errorMessage" value="${errorMessage}" readonly="readonly"/>
	</div>
</c:if>

<form:form class="semantic" method="POST" action="${pageContext.request.contextPath}/bank-operation" modelAttribute="bankOperation">
	<fieldset>
		<legend>Add Bank Operation(Deposit/Withdraw)</legend>

		<input type="hidden" name="clientName" id="clientName" value="${bankOperation.clientName}" readonly="readonly" />
		<div>
			<label for="accountNumber">Account Number</label> <input id="accountNumber" type="text" name="accountNumber" value="${bankOperation.accountNumber}" readonly="readonly" />
		</div>
		<div>
			<label for="currentBalance">Current Account Balance</label> <input id="currentBalance" name="currentBalance" class="money" value="${bankOperation.accountBalance}" readonly="readonly" />
			<b>&nbsp;${bankOperation.accountCurrency}</b>
		</div>

		<div>
			<label for="operationType">Operation</label>
			<select id="operationType" name="operationType">
				<option value="deposit">Deposit</option>
				<option value="withdraw">Withdraw</option>
				<option value="read_balance">Read Balance</option>
			</select>
		</div>
		<div>
			<label for="transactionAmount">Amount to change</label> <input id="transactionAmount" name="transactionAmount" class="money"/>
		</div>
		<select id="transactionCurrency" name="transactionCurrency">
<c:choose>
	<c:when test="${bankOperation.accountCurrency.equals('BGN')}">
		<option selected="selected" value="BGN">BGN</option>
	</c:when>
	<c:otherwise>
		<option value="BGN">BGN</option>		
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${bankOperation.accountCurrency.equals('EUR')}">
		<option selected="selected" value="EUR">EUR</option>
	</c:when>
	<c:otherwise>
		<option value="EUR">EUR</option>		
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${bankOperation.accountCurrency.equals('USD')}">
		<option selected="selected" value="USD">USD</option>
	</c:when>
	<c:otherwise>
		<option value="USD">USD</option>		
	</c:otherwise>
</c:choose>
		</select>

		<select id="transactionDate" name="transactionDate">
			<option value="10.02.2016">10.02.2016</option>
			<option value="11.02.2016">11.02.2016</option>
			<option value="12.02.2016">12.02.2016</option>
		</select>
	</fieldset>

	<div class="button-row">
		<a href="${pageContext.request.contextPath}/bank-register">Cancel</a> or <input type="submit" value="Submit" />
	</div>
</form:form>