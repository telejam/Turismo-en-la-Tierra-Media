<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>

<style>
body {
	padding-top: 50px;
}
</style>
<script src="/assets/js/scripts.js" defer></script>
</head>

<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">
	
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Nueva Promoción</h1>
		</div>

		<c:if test="${promotion != null && !promotion.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la Promocion.</p>
			</div>
		</c:if>

		<form action="/TierraMedia3/promotions/create.do" method="post">
			<jsp:include page="/views/promotions/form.jsp"></jsp:include>
		</form>
	</main>
	
	<br />
	
</body>
</html>