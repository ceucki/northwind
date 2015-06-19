<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' href='<c:url value="/styles/default.css"/>'>
<title>Northwind</title>
</head>
<body>
	<h1>Order detail</h1>
	<table>
		<thead>
			<tr>
				<td>Product id</td>
				<td>product name</td>
				<td>unit price</td>
				<td>quantity</td>
				<td>value</td>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<td>Total</td>
				<td></td>
				<td></td>
				<td></td>
				<td><fmt:formatNumber value="${order.total}"/></td>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach var="orderdetail" items="${order.orderdetails}">

				<tr>
					<td>${orderdetail.product.id}</td>
					<td>${orderdetail.product.name}</td>
					<td>€ <fmt:formatNumber value="${orderdetail.unitPrice}" /></td>
					<td><fmt:formatNumber value="${orderdetail.quantity}" /></td>
					<td><fmt:formatNumber
							value="${orderdetail.value}" /></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</body>
</html>