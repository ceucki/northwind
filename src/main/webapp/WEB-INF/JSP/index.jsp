<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<!doctype html>
<html lang='nl'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='<c:url value="/styles/default.css"/>'>
<title>Northwind</title>
</head>
<body>
	<h1>Countries</h1>
	<c:forEach var="country" items="${countries}">
		<c:url value="/index.htm" var="detailURL">
			<c:param name="id" value="${country.id}"></c:param>
		</c:url>
		<li><a href="${detailURL}">${country.name}</a></li>
	</c:forEach>

	<c:if test="${not empty country}">
		<h2>Customers</h2>
		<c:forEach var="customer" items="${country.customers}">
			<li><c:url value="/orders.htm" var="detailURL">
					<c:param name="id" value="${customer.id}" />
				</c:url><a href="${detailURL}">${customer.name}</a></li>
			<li>${customer.adres.address }</li>
			<li>${customer.adres.postalcode}</li>
			<li>${customer.adres.city}</li>
			<br />
		</c:forEach>
	</c:if>
</body>
</html>