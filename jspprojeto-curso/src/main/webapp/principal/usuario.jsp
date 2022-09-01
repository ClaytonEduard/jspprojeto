<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cad. Usuário</h4>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">
															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	value="${modelLogin.id}" class="form-control">
																<span class="form-bar"></span> <label
																	class="float-label" for="id">ID:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome}" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label" for="nome">Nome: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	value="${modelLogin.email}" class="form-control"
																	placeholder="Entre com e-mail" required="required"
																	autocomplete="off"> <span class="form-bar"></span>
																<label class="float-label" for="email">E-mail:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	value="${modelLogin.login}" class="form-control"
																	placeholder="Entre com login" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="nome">Login: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="password" id="password"
																	value="${modelLogin.password}" class="form-control"
																	placeholder="Entre com a senha" required="required"
																	autocomplete="off"> <span class="form-bar"></span>
																<label class="float-label" for="password">Senha</label>
															</div>
															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-danger waves-effect waves-light"
																onclick="criarDelete()">Excluir</button>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span>${msg}</span>
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

	<script type="text/javascript">
	
		function criarDelete() {
			
			if(confirm('Deseja realmente excluir os dados?')){
			document.getElementById("formUser").method = 'get';
			document.getElementById("acao").value = 'deletar';
			document.getElementById("formUser").submit();
			}
		}

		function limparForm() {

			/*Retorna os elementos html dentro do form*/
			var elementos = document.getElementById("formUser").elements;
			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}

		}
	</script>

</body>

</html>
