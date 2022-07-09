<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bem vindo Curso JSP</title>
</head>
<body>

	<h1>Login</h1>

		<form action="ServletLogin" method="POST">
			<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
			<table>
				<tr>
					<td>Login</td>
					<td><input name="login" type="text" /></td>
				</tr>
				<tr>
					<td>Senha</td>
					<td><input name="senha" type="password" /></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Enviar"></td>
				</tr>
			</table>
		</form>


		<h4>${msg}</h4>


</body>
</html>