<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>


<style>
body {
   background-image: url('https://www.bibitepaoletti.com/wp-content/uploads/2019/05/vintage-1659117_1280-1280x533.jpg'); 
   
   background-repeat: no-repeat;
   background-attachment: fixed;
   background-size: cover;
   background-position: center;

   color: #FFFFFF;
}

h1{
    color: #800000;
}


</style>




</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Su Itinerario es el siguiente:</h1>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Costo</th>
					<th>Duración</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${offers}" var="offer">
					<tr>
						<td><strong><c:out value="${offer.name}"></c:out></strong>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Cras pretium eros urna. Sed quis erat congue, bibendum tortor
								malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
						<td><c:out value="${offer.getCost()}"></c:out></td>
						<td><c:out value="${offer.getDuration()}"></c:out></td>

						<td>
							<c:if test="${!user.isAdmin()}">
								
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>