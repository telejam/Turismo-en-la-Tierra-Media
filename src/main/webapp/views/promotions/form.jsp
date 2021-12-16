<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="name" required
		value="${promotion.name}">
</div>
<div class="mb-3">
	<label for="cost"
		class='col-form-label ${attraction.errors.get("cost") != null ? "is-invalid" : "" }'>Costo:</label>
	<input class="form-control" type="number" id="cost" name="cost"
		required value="${promotion.cost}"></input>
	<div class="invalid-feedback">
		<c:out value='${promotion.errors.get("cost")}'></c:out>
	</div>
</div>

<h6>Tipo de promocion</h6>
<select class="form-select" aria-label="Default select example"
			name="type" required>
			<option value="Absoluta">Absoluta</option>
			<option value="Porcentual">Porcentual</option>
			<option value="AxB">AxB</option>
		</select>
		<br><br>
	
 <h6>Moria</h6>
<div id="attraction1">
    <input type="checkbox" value="2" name="included" /> Included<br/>
    <input type="checkbox" value="1" name="free" /> Free<br/>
   
</div>

<h6>Minas tirith</h6>
<div id="attraction1">
    <input type="checkbox" value="2"  name="included" /> Included<br/>
    <input type="checkbox" value="2"  name="free" /> Free<br/>
   
</div>

<h6>Gondor</h6>
<div id="attraction1">
    <input type="checkbox" value="3" name="included" /> Included<br/>
    <input type="checkbox" value="3" name="free" /> Free<br/>

</div>
        
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>