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
	<h1>Orders</h1>
	<c:forEach var="order" items="${customer.orders}">
		<h2>Order</h2>
		<c:url value="/orderdetail.htm" var="detailURL">
			<c:param name="id" value="${order.id}" />
		</c:url>
		<dt>id</dt>
		<dd>
			<a href="${detailURL}">${order.id}</a>
		</dd>
		<dt>Ordered</dt>
		<dd>${order.ordered}</dd>
		<dt>Required</dt>
		<dd>${order.required }</dd>
		<dt>Shipped</dt>
		<dd>${order.shipped}</dd>
	</c:forEach>

</body>
</html>