package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;

/**
 * Servlet Filter implementation class FilterAuteticacao
 */
@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todas as requis q vierem do projeto ou mapeamento
public class FilterAuteticacao extends HttpFilter implements Filter {

	private static Connection connection;

	private static final long serialVersionUID = 1L;

	public FilterAuteticacao() {
		super();
	}

	// encera os processo quando o servidor é parado
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// intecerpta todas as requisicoes e retorna as respostas
	// tudo do sistema passa por ele

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;

			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath();// url esta sendo acessada

			// validar se esta logado, senao redireciona para o login

			if (usuarioLogado == null && !urlParaAutenticar.contains("/principal/ServletLogin")) {

				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redirecionar.forward(request, response);
				return;// para a execusao
			} else {
				chain.doFilter(request, response);
			}
			connection.commit(); // deu tudo certo conecta 

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}

	// inicia os processos ou recursos quando o servidor sob o projeto
	// incia a conexao com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
