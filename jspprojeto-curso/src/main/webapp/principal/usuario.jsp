<%@ page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="head.jsp"></jsp:include>

<body>

	<jsp:include page="theme-loader.jsp"></jsp:include>
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

														<form class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">
															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	value="${modelLogin.id}" class="form-control">
																<span class="form-bar"></span> <label
																	class="float-label" for="id">ID:</label>
															</div>

															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<c:if
																		test="${modelLogin.fotouser != '' && modelLogin.fotouser != null}">
																		<a
																			href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
																			<img alt="Imagem User" id="fotoembase64"
																			src="${modelLogin.fotouser}" width="70px">
																		</a>
																	</c:if>
																	<c:if
																		test="${modelLogin.fotouser == ''|| modelLogin.fotouser == null}">
																		<img alt="Imagem User" id="fotoembase64"
																			src="assets/images/avatar-1.jpg" width="70px">
																	</c:if>
																</div>
																<input type="file" id="filefoto" name="filefoto"
																	accept="image/*"
																	onchange="visualizarImg('fotoembase64','filefoto');"
																	class="form-control-file"
																	style="margin-top: 15px; margin-left: 5px">
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome}" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label" for="nome">Nome: </label>
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="dataNascimento"
																	id="dataNascimento"
																	value="${modelLogin.dataNascimento}"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label"
																	for="nome">Dat. Nascimento: </label>
															</div>


															<div class="form-group form-default form-static-label">
																<input type="text" name="rendamensal" id="rendamensal"
																	value="${modelLogin.rendaMensal}" class="form-control"
																	required="required"> <span class="form-bar"></span>
																<label class="float-label" for="nome">Renda
																	Mensal: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	value="${modelLogin.email}" class="form-control"
																	placeholder="Entre com e-mail" required="required"
																	autocomplete="off"> <span class="form-bar"></span>
																<label class="float-label" for="email">E-mail:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled">[Selecione o
																		perfil]</option>
																	<option value="ADMIN"
																		<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Admin</option>
																	<option value="SECRETARIA"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");
if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Secret�ria</option>
																	<option value="AUXILIAR"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");
if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {
	out.print(" ");
	out.print("selected=\"selected\"");
	out.print(" ");
}%>>Auxiliar</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label" for="nome">Perfil: </label>
															</div>

															<!-- inicio dos dados de endere�o -->

															<div class="form-group form-default form-static-label">
																<input onblur="pesquisaCep()" type="text" name="cep"
																	id="cep" value="${modelLogin.cep}" class="form-control"
																	placeholder="Entre com CEP" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="cep">CEP: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="logradouro" id="logradouro"
																	value="${modelLogin.logradouro}" class="form-control"
																	placeholder="Entre com logradouro" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="logradouro">Rua: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	value="${modelLogin.bairro}" class="form-control"
																	placeholder="Entre com bairro" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="bairro">Bairro: </label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="localidade" id="localidade"
																	value="${modelLogin.localidade}" class="form-control"
																	placeholder="Entre com localidade" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="localidade">Localidade:
																</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="uf" id="uf"
																	value="${modelLogin.uf}" class="form-control"
																	placeholder="Entre com uf" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="uf">Estado: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	value="${modelLogin.numero}" class="form-control"
																	placeholder="Entre com numero" required="required">
																<span class="form-bar"></span> <label
																	class="float-label" for="numero">N�mero: </label>
															</div>
															<!-- fim do endere�o -->


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

															<div class="form-group form-default form-static-label">

																<input type="radio" name="sexo" checked="checked"
																	value="MASCULINO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");
}%>>Masculino</>
																<input type="radio" name="sexo" value="FEMININO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")) {
	out.print(" ");
	out.print("checked=\"checked\"");
	out.print(" ");
}%>>Feminino</>

															</div>

															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-danger waves-effect waves-light"
																onclick="criarDeleteComAjax();">Excluir</button>

															<c:if test="${modelLogin.id > 0}">

																<a
																	href="<%= request.getContextPath() %>/ServletTelefone?iduser=${modelLogin.id}"
																	class="btn btn-secondary waves-effect waves-light">Telefone</a>
															</c:if>
															<!-- Button trigger modal -->
															<button type="button" class="btn btn-info"
																data-toggle="modal" data-target="#exampleModalUsuario">
																Pesquisar</button>
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
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${modelLogins}' var='ml'>
														<tr>
															<td><c:out value="${ml.id}"></c:out></td>
															<td><c:out value="${ml.nome}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">
																	Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<nav aria-label="Page navigation example">
											<ul class="pagination">
												<%
												int totalPagina = (int) request.getAttribute("totalPagina");
												for (int p = 0; p < totalPagina; p++) {
													String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 5);
													out.print("<li class=\"page-item\"><a class=\"page-link\"href=\"" + url + "\">" + (p + 1) + "</a></li>");
												}
												%>
											</ul>
										</nav>
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


	<!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisar
						Usu�rio</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="nomeBusca" aria-label="nome" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success" type="button"
								onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaresultados">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>

					<nav aria-label="Page navigation example">
						<ul class="pagination" id="ulPaginacaoUserAjax">

						</ul>
					</nav>
					<span id="totalResultados"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		// funcao para a renda
		$("#rendamensal").maskMoney({
			showSymbol : true,
			symbol : "R$ ",
			decimal : ",",
			thousands : "."
		});
		//formatador monetario javascript
		const formatter = new Intl.NumberFormat('pt-BR', {
			currency : 'BRL',
			minimumFractionDigits : 2

		});
		
		$("#rendamensal").val(formatter.format($("#rendamensal").val()));
		
		$("#rendamensal").focus();
		
		// fim funcao renda -------------------
		
		//-- inicio conversao data nascimento
		var dataNascimento = $("#dataNascimento").val();
		
		var dateFormat = new Date(dataNascimento);
		
		$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));
		
		$("#nome").focus();

		// funcao para traduzir o calendario
		$(function() {

			$("#dataNascimento")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Ter�a',
										'Quarta', 'Quinta', 'Sexta', 'S�bado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'S�b', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Mar�o',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Pr�ximo',
								prevText : 'Anterior'
							});
		});

		//funcao jquery para aceitar somente numeros no campo
		$("#numero").keypress(function(event) {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});

		//funcao jquery para aceitar somente numeros no campo
		$("#cep").keypress(function(event) {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});

		function limpa_formul�rio_cep() {
			// Limpa valores do formul�rio de cep.

			$("#logradouro").val("");
			$("#bairro").val("");
			$("#localidade").val("");
			$("#uf").val("");
			$("#numero").val("");
		}

		function pesquisaCep() {
			//recebe os dados do campo
			var cep = $("#cep").val();

			//Express�o regular para validar o CEP.
			var validacep = /^[0-9]{8}$/;
			if (validacep.test(cep)) {

				$.getJSON("https://viacep.com.br/ws/" + cep
						+ "/json/?callback=?", function(dados) {

					if (!("erro" in dados)) {
						$("#cep").val(dados.cep);
						$("#logradouro").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
						$("#localidade").val(dados.localidade);
						$("#uf").val(dados.uf);
					} else {
						limpa_formul�rio_cep();
						alert("CEP n�o encontrado.");
					}

				});
			}//end if.
			else {
				//cep � inv�lido.
				limpa_formul�rio_cep();
				alert("Formato de CEP inv�lido.");
			}
		}

		// funcao upload foto
		function visualizarImg(fotoembase64, filefoto) {

			// campo preview
			var preview = document.getElementById(fotoembase64);
			// file do user
			var fileUser = document.getElementById(filefoto).files[0];

			var reader = new FileReader();

			reader.onloadend = function() {
				preview.src = reader.result; // carrega a foto na tela
			};
			if (fileUser) {
				reader.readAsDataURL(fileUser); // preview da imagem 
			} else {
				preview.src = ''
			}

		}

		// ver editar
		function verEditar(id) {

			var urlAction = document.getElementById('formUser').action;

			window.location.href = urlAction + '?acao=buscarEditar&id=' + id; // executa o get

		}

		// buscar user pagina��o 
		function buscaUserPagAjax(url) {
			var urlAction = document.getElementById('formUser').action;
			var nomeBusca = document.getElementById('nomeBusca').value;
			$
					.ajax(
							{
								method : "get",
								url : urlAction,
								data : url,
								success : function(response, textStatus, xhr) {

									var json = JSON.parse(response);

									$('#tabelaresultados > tbody > tr')
											.remove(); //entro na tabela e removo todas as linhas para mostrar um novo resultado
									$("#ulPaginacaoUserAjax > li").remove();

									for (var i = 0; i < json.length; i++) {
										$('#tabelaresultados > tbody')
												.append(
														'<tr> <td>'
																+ json[i].id
																+ '</td> <td>'
																+ json[i].nome
																+ '</td> <td><button onclick="verEditar('
																+ json[i].id
																+ ')" type="button" class="btn btn-info">Ver</button></td></tr>'); // adcionar novos dados
									}

									/*valores do total da tabela*/
									document.getElementById('totalResultados').textContent = 'Resultados: '
											+ json.length;
									/*colocando paginacao no dialog */
									var totalPagina = xhr
											.getResponseHeader("totalPagina");
									for (var p = 0; p < totalPagina; p++) {
										var url = 'nomeBusca='
												+ nomeBusca
												+ '&acao=buscarUserAjaxPage&pagina='
												+ (p * 5);
										$("#ulPaginacaoUserAjax")
												.append(
														'<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''
																+ url
																+ '\')">'
																+ (p + 1)
																+ '</a></li>');

									}

								}
							}).fail(
							function(xhr, status, errorThrown) {
								alert('Erro ao buscar usu�rio por nome: '
										+ xhr.responseText);
							});
		}

		// buscar usuario 

		function buscarUsuario() {
			var nomeBusca = document.getElementById('nomeBusca').value;
			// so busca no banco se tiver dados
			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
				var urlAction = document.getElementById('formUser').action;

				$
						.ajax(
								{
									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=buscarUserAjax',
									success : function(response, textStatus,
											xhr) {
										var json = JSON.parse(response);

										$('#tabelaresultados > tbody > tr')
												.remove(); //entro na tabela e removo todas as linhas para mostrar um novo resultado
										$("#ulPaginacaoUserAjax > li").remove();

										for (var i = 0; i < json.length; i++) {
											$('#tabelaresultados > tbody')
													.append(
															'<tr> <td>'
																	+ json[i].id
																	+ '</td> <td>'
																	+ json[i].nome
																	+ '</td> <td><button onclick="verEditar('
																	+ json[i].id
																	+ ')" type="button" class="btn btn-info">Ver</button></td></tr>'); // adcionar novos dados

										}

										/*valores do total da tabela*/
										document
												.getElementById('totalResultados').textContent = 'Resultados: '
												+ json.length;
										/*colocando paginacao no dialog */
										var totalPagina = xhr
												.getResponseHeader("totalPagina");
										for (var p = 0; p < totalPagina; p++) {
											var url = 'nomeBusca='
													+ nomeBusca
													+ '&acao=buscarUserAjaxPage&pagina='
													+ (p * 5);
											$("#ulPaginacaoUserAjax")
													.append(
															'<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''
																	+ url
																	+ '\')">'
																	+ (p + 1)
																	+ '</a></li>');
										}

									}
								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao buscar usu�rio por nome: '
											+ xhr.responseText);
								});

			}
		}

		// delete com ajax
		function criarDeleteComAjax() {

			if (confirm('Deseja realmente excluir os dados?')) {

				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarajax',
					success : function(response) {
						limparForm();
						document.getElementById('msg').textContent = response;
					}
				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usu�rio por ID: '
									+ xhr.responseText);
						});
			}

		}

		// delete com javascript
		function criarDelete() {

			if (confirm('Deseja realmente excluir os dados?')) {
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
