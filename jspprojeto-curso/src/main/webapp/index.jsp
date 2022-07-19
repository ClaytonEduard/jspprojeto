<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Bem vindo Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
}

h1 {
	position: absolute;
	top: 30%;
	left: 33%;
}

.msg {
	position: absolute;
	top: 20%;
	left: 38%;
	color: red;
}	
</style>

</head>
<body>

	<h1>Bem Vindo</h1>

	<form class="mb-3 needs-validation" action="<%= request.getContextPath() %>/ServletLogin"
		method="POST" novalidate="novalidate">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<div class="mb-3">
			<label for="login" class="form-label">Login</label> <input id="login"
				class="form-control" name="login" type="text" required="required" />
			<div class="invalid-feedback">Informe o Login</div>
			<div class="valid-feedback">Ok!</div>
		</div>
		<div class="mb-3">
			<label for="passowrd" class="form-label">Senha</label> <input
				id="passowrd" class="form-control" name="password" type="password"
				required="required" />
			<div class="invalid-feedback">Informe a Senha</div>
			<div class="valid-feedback">Ok!</div>
		</div>
		<div>
			<input class="btn btn-primary" type="submit" value="Logar">
		</div>
	</form>


	<h4 class="msg">${msg}</h4>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>