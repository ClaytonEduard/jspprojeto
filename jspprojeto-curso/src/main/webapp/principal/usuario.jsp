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
														<h4 class="sub-title">Cad. Usu�rio</h4>

														<form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post"> 
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	class="form-control"> <span class="form-bar"></span>
																<label class="float-label" for="id">ID:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label"
																	for="nome">Nome: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" placeholder="Enter Email"
																	required="required" autocomplete="off"> <span class="form-bar"></span> <label
																	class="float-label" for="email">E-mail:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="password" id="password"
																	class="form-control" placeholder="Enter Password"
																	required="required" autocomplete="off"> <span class="form-bar"></span> <label
																	class="float-label" for="password">Senha</label>
															</div>
															<button class="btn btn-primary waves-effect waves-light">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button class="btn btn-info waves-effect waves-light">Excluir</button>

														</form>
													</div>
												</div>
											</div>
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
