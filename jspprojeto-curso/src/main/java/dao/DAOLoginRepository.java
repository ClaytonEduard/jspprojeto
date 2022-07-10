package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;

	// definir o construtor
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
													//upper login convertido para maiuculo
		String sql = "select * from model_login where upper(login) = upper(?) and upper(password) = upper(?)";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, modelLogin.getLogin());

		statement.setString(2, modelLogin.getPassword());

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			// autenticado 
			return true;

		}
		// não autenticado 
		return false;

	}

}
