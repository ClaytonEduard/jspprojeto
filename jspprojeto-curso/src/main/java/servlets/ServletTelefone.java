package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/ServletTelefone/")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

	public ServletTelefone() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String iduser = request.getParameter("iduser");

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(iduser));
				List<ModelTelefone> modelTelefones =  daoTelefoneRepository.listFone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("modelLogin", modelLogin);
				/*
				 * Retorno para a tela
				 */
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				// retonnando a paginacao
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");

			ModelTelefone modelTelefone = new ModelTelefone();
			modelTelefone.setNumero(numero);
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id)));
			modelTelefone.setUsuario_cad_id(super.getUserLogadoObjt(request));
			
			daoTelefoneRepository.gravaTelefone(modelTelefone);
			
			List<ModelTelefone> modelTelefones =  daoTelefoneRepository.listFone(Long.parseLong(usuario_pai_id));
			request.setAttribute("modelTelefones", modelTelefones);
			request.setAttribute("msg", "Salvo com sucesso");
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
