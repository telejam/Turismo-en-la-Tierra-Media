<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las ofertas de la Tierra Media</h1>
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Costo</th>
					<th>Duración</th>
					<th>Cupo</th>
					<th>Acciones</th>
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
						<td><c:out value="${offer.getCapacity()}"></c:out></td>

						<td>
							<c:if test="${!user.isAdmin()}">
								<c:choose>
									<c:when
										test="${user.canAfford(offer) && user.canAttend(offer) && offer.canHost(1)}">
										<a href="/TierraMedia3/offers/buy.do?id=${offer.id}&type=${offer.getContent().size()}"
											class="btn btn-success rounded" role="button">Comprar</a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											role="button">No se puede comprar</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>