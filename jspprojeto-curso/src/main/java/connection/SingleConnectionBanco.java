package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://localhost:5434/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String passowrd = "postgres";
	private static Connection connection = null;

	// Retorna a conexao com o banco
	public static Connection getConnection() {
		return connection;
	}

	// chamando a Classe direta conect
	static {
		conectar();
	}

	// coonstrutor
	// caso instaciamos uma classe nova e feita a conexa
	public SingleConnectionBanco() { // quanto for solicitado vai conectar
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");// carrega o drive de connecao
				connection = DriverManager.getConnection(banco, user, passowrd);
				connection.setAutoCommit(false);// para nao efeturar ateracoes sem comando do user
			}
		} catch (Exception e) {
			e.printStackTrace();// mostrar qualquer erro no momento de conectar
		}
	}

}
