<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> 
	<input type="text" class="form-control" id="name" name="name" required value="${promotion.name}"></input>
</div>

<div class="mb-3">
	<label for="type" class="col-form-label">Tipo de promocion:</label> 
	<select class="form-select" aria-label="Default select example" id="type" name="type" required>
		<option value="Absoluta" ${promotion.type == "Absoluta" ? " selected " : "" }>Absoluta</option>
		<option value="Porcentual" ${promotion.type == "Porcentual" ? " selected " : "" }>Porcentual</option>
		<option value="AxB" ${promotion.type == "AxB" ? " selected " : "" }>AxB</option>
	</select>
</div>

<div class="mb-3">
	<label for="value" class='col-form-label ${promotion.errors.get("value") != null ? "is-invalid" : "" }'>Costo:</label>
	<input class="form-control" type="number" id="value" name="value" required value="${promotion.value}"></input>

	<div class="invalid-feedback">
		<c:out value='${promotion.errors.get("value")}'></c:out>
	</div>
</div>

<c:forEach items="${attractions}" var="attraction">

	<h6><c:out value="${attraction.name}"></c:out></h6>
	<div id="attraction1">
	    <input type="checkbox" value='<c:out value="${attraction.id}"></c:out>' name="included" 
		${included.contains(attraction.id)||free.contains(attraction.id) ? " checked " : "" } /> Incluída<br/>
		
	    <input type="checkbox" value='<c:out value="${attraction.id}"></c:out>' name="free" 
		${free.contains(attraction.id) ? " checked " : "" }/> Gratis<br/>
	</div>
	<hr>

</c:forEach>

<br> 
<div>  
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary" role="button">Cancelar</a>
</div>