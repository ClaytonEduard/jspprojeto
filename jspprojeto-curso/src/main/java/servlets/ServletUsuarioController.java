package servlets;

import java.io.IOException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// instaciando o dao usuario
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao  =  request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase(acao)) {
			String idUser = request.getParameter("id");
		}
		

	}

	// metodo doPOST que captura os dados do formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			ModelLogin modelLogin = new ModelLogin();
			// se o id for diverente de null e diferente de vazio, recebe a Conversao String
			// pra long senao recebe nulo
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setPassword(password);

			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe um usuário com o mesmo login, informe outro login.";
			} else {
				if(modelLogin.isNovo()) {
					msg = "Gravado com sucesso";
				}else {
					msg = "Atualizado com sucesso";
				}
				// salva o usuario
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}

			// setar a mensagem no dialog
			request.setAttribute("msg", msg);

			// seta os dados da pagina para que nao sejam perdidos, tambem possibilidade de
			// editar os dados
			request.setAttribute("modelLogin", modelLogin);

			// redirecionar para a pagina de usuario
			// modo 1 de ussar
			/*
			 * RequestDispatcher redireciona =
			 * request.getRequestDispatcher("principal/usuario.jsp");
			 * redireciona.forward(request, response);
			 */
			// modo 2
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
