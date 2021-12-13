<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Usuario:</label> <input
		type="text" class="form-control" id="name" name="username" placeholder="Ingrese un usuario"
		required value="${user.username}">
</div>
<div class="mb-3">
	<label for="coins"
		class='col-form-label ${tmp_user.errors.get("coins") != null ? "is-invalid" : "" }'>Monedas:</label>
	<input class="form-control" type="number" id="coins" name="coins" placeholder="Ingrese un numero positivo"
		required value="${user.coins}"></input>
	<div class="invalid-feedback">
	
		<%--  <c:out value='${tmp_user.errors.get("coins")}'></c:out> --%>
		<c:out value='${tmp_user.errors.get("coins")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="time"
		class='col-form-label ${tmp_user.errors.get("time") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" id="time" name="time" placeholder="Ingrese un numero positivo"
		required value="${user.time}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("time")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="password"
		class='col-form-label ${tmp_user.errors.get("password") != null ? "is-invalid" : "" }'>Contraseña:</label>
	<input class="form-control" id="password" name="password" placeholder="Ingrese 4 digitos alfa/numericos"
		required value="${user.password}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_user.errors.get("password")}'></c:out>
	</div>
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
