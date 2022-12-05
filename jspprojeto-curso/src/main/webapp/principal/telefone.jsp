<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>

	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!--  paramos no minuto 14:00 do video jsp include -->
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>
			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cad. Telefone</h4>
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletTelefone"
															method="post" id="formFone">


															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	value="${modelLogin.id}" class="form-control">
																<span class="form-bar"></span> <label
																	class="float-label" for="id">ID User:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" readonly="readonly" name="nome"
																	id="nome" value="${modelLogin.nome}"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label"
																	for="nome">Nome: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero" value=""
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label"
																	for="nome">Numero: </label>
															</div>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>

										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaresultadosviews">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Numero</th>
														<th scope="col">Excluir</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${modelTelefones}' var='f'>
														<tr>
															<td><c:out value="${f.id}"></c:out></td>
															<td><c:out value="${f.numero}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${ml.id}">
																	Excluir</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>
