<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head><jsp:include page="/partials/head.jsp"></jsp:include>
<script src="/TierraMedia3/assets/js/scripts.js" defer></script>
</head>
<body>
	<jsp:include page="/partials/nav.jsp"></jsp:include>
	<main class="container">
		<c:if test="${promotion != null && !promotion.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al actualizar la promoción.</p>
			</div>
		</c:if>
		<form action="/TierraMedia3/promotions/edit.do" method="post">
			<input type="hidden" name="id" value="${promotion.id}">
			<jsp:include page="/views/promotions/form.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>