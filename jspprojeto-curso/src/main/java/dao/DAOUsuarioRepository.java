package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	// importa a conexao
	private Connection connection;

	// inicia o contrutor
	public DAOUsuarioRepository() {

		// chama a conexao para o DAOUser
		connection = SingleConnectionBanco.getConnection();

	}

	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {

		if (objeto.isNovo()) {
			// grava novo
			String sql = "insert into model_login(login, password, nome, email) values (?,?,?,?)";
			PreparedStatement preparedSql = connection.prepareStatement(sql);

			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getPassword());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());

			// salvar os dados
			preparedSql.execute();
			connection.commit();
		} else {
			String sql = "update model_login set login=?, password=?, nome=?, email=? where id=" + objeto.getId() + ";";
			PreparedStatement preparedSql = connection.prepareStatement(sql);

			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getPassword());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.executeUpdate();
			connection.commit();
		}
		return this.consultaUsuario(objeto.getLogin());
	}

	/* retorna todos os users*/
	public List<ModelLogin> consultaUsuarioList() throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "SELECT * from model_login";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		/* varer as linhas do resultado do sql */

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			// modelLogin.setPassword(resultSet.getString("password"));
			retorno.add(modelLogin);
		}

		return retorno;
	}
	
	
	
	
	
	public List<ModelLogin> consultaUsuarioList(String nome) throws Exception {
		ModelLogin modelLogin = new ModelLogin();

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "SELECT * from model_login where upper(nome) like upper(?)";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1,
				"%" + nome + "%"); /* subistitui o ? do sql para os dados que estao dentro das as duplas */

		ResultSet resultSet = statement.executeQuery();

		/* varer as linhas do resultado do sql */

		while (resultSet.next()) {
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			// modelLogin.setPassword(resultSet.getString("password"));
			retorno.add(modelLogin);
		}

		return retorno;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper('" + login + "')";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));

		}

		return modelLogin;
	}

	// metodo consultar usuario por id

	public ModelLogin consultaUsuarioID(String id) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));

		}

		return modelLogin;
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('" + login + "')";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		resultSet.next();
		return resultSet.getBoolean("existe");

	}

	// metodo deletar user
	public void deletarUser(String idUser) throws Exception {

		String sql = "delete from model_login where id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(idUser));

		statement.executeUpdate();
		connection.commit();
	}

}
