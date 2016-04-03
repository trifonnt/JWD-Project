<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${param.title}</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/include/header.jsp"/>
	<h1>${param.title}</h1>
	<jsp:include page="/WEB-INF/${param.content}.jsp"/>
	<jsp:include page="/WEB-INF/include/footer.jsp">
		<jsp:param name="copyrightYear" value="2016"/>
		<jsp:param name="projectName" value="SoftUni :: Java Web Development - January 2016 :: Web Shop Project"/>
		<jsp:param name="projectVersion" value="1.0.1"/>
	</jsp:include>
</body>
</html>