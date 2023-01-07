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

	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception {

		if (objeto.isNovo()) {
			// grava novo
			String sql = "insert into model_login(login, password, nome, email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero, datanascimento, rendamensal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedSql = connection.prepareStatement(sql);

			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getPassword());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setLong(5, userLogado);
			preparedSql.setString(6, objeto.getPerfil());
			preparedSql.setString(7, objeto.getSexo());
			preparedSql.setString(8, objeto.getCep());
			preparedSql.setString(9, objeto.getLogradouro());
			preparedSql.setString(10, objeto.getBairro());
			preparedSql.setString(11, objeto.getLocalidade());
			preparedSql.setString(12, objeto.getUf());
			preparedSql.setString(13, objeto.getNumero());
			preparedSql.setDate(14, objeto.getDataNascimento());
			preparedSql.setDouble(15, objeto.getRendaMensal());
			// salvar os dados
			preparedSql.execute();
			connection.commit();

			/*
			 * inserindo a foto no banco de dados se realmente foi inserido uma foto ou não
			 * foi inserido damos um update no cadastro realizado
			 * 
			 */

			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "update model_login set fotouser =?, extensaofotouser=? where login =?";
				preparedSql = connection.prepareStatement(sql);
				preparedSql.setString(1, objeto.getFotouser());
				preparedSql.setString(2, objeto.getExtensaofotouser());
				preparedSql.setString(3, objeto.getLogin());
				// salvar os dados
				preparedSql.execute();
				connection.commit();

			}

		} else {
			String sql = "update model_login set login=?, password=?, nome=?, email=?, perfil=?, sexo =?,cep =?, logradouro=?, bairro=?, localidade=?, uf=?, numero=?, datanascimento=?, rendamensal=? where id="
					+ objeto.getId() + ";";
			PreparedStatement preparedSql = connection.prepareStatement(sql);

			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getPassword());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setString(5, objeto.getPerfil());
			preparedSql.setString(6, objeto.getSexo());
			preparedSql.setString(7, objeto.getCep());
			preparedSql.setString(8, objeto.getLogradouro());
			preparedSql.setString(9, objeto.getBairro());
			preparedSql.setString(10, objeto.getLocalidade());
			preparedSql.setString(11, objeto.getUf());
			preparedSql.setString(12, objeto.getNumero());
			preparedSql.setDate(13, objeto.getDataNascimento());
			preparedSql.setDouble(14, objeto.getRendaMensal());

			preparedSql.executeUpdate();
			connection.commit();

			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "update model_login set fotouser =?, extensaofotouser=? where id =?";

				preparedSql = connection.prepareStatement(sql);

				preparedSql.setString(1, objeto.getFotouser());
				preparedSql.setString(2, objeto.getExtensaofotouser());
				preparedSql.setLong(3, objeto.getId());
				// salvar os dados
				preparedSql.execute();
				connection.commit();

			}
		}
		return this.consultaUsuario(objeto.getLogin(), userLogado);
	}

	// paginação
	public int totalPagina(Long userLogado) throws Exception {

		String sql = "select count(1) as total from model_login where usuario_id = " + userLogado;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		resultSet.next();

		Double cadastros = resultSet.getDouble("total");

		Double porpagina = 5.0;

		Double pagina = cadastros / porpagina;

		Double resto = pagina % 2;
		if (resto > 0) {
			pagina++;
		}

		return pagina.intValue();

	}

	// consulta usuario list paginado
	public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado
				+ " order by nome offset " + offset + " limit 5";

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
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	/* retorna todos os users */
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + "limit 5";

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
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	/* lista de dados paginados do dialog de pesquisa */
	public int consultaUsuarioListTotalPaginaPaginacao(String nome, Long userLogado) throws Exception {

		String sql = "select count(1) as total from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1,
				"%" + nome + "%"); /* subistitui o ? do sql para os dados que estao dentro das as duplas */
		statement.setLong(2, userLogado);
		ResultSet resultSet = statement.executeQuery();

		resultSet.next();

		Double cadastros = resultSet.getDouble("total");

		Double porpagina = 5.0;

		Double pagina = cadastros / porpagina;

		Double resto = pagina % 2;
		if (resto > 0) {
			pagina++;
		}

		return pagina.intValue();
	}

	public List<ModelLogin> consultaUsuarioListOffSet(String nome, Long userLogado, Integer offset) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? offset "
				+ offset + " limit 5";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();

		/* varer as linhas do resultado do sql */

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			// modelLogin.setPassword(resultSet.getString("password"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1,
				"%" + nome + "%"); /* subistitui o ? do sql para os dados que estao dentro das as duplas */
		statement.setLong(2, userLogado);
		ResultSet resultSet = statement.executeQuery();

		/* varer as linhas do resultado do sql */

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			// modelLogin.setPassword(resultSet.getString("password"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			retorno.add(modelLogin);
		}

		return retorno;
	}

	public ModelLogin consultaUsuarioLogado(String login) throws Exception {

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
			modelLogin.setUseradmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));

		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper('" + login + "') and useradmin is false ";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));
			modelLogin.setUseradmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper('" + login
				+ "') and useradmin is false and usuario_id = " + userLogado;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));

		}

		return modelLogin;
	}

	// metodo consultar usuario por id

	public ModelLogin consultaUsuarioID(Long id) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ? and useradmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setExtensaofotouser(resultSet.getString("extensaofotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
		}

		return modelLogin;
	}

	// metodo consultar usuario por id + usuario logado

	public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ? and useradmin is false  and usuario_id =?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setPassword(resultSet.getString("password"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setExtensaofotouser(resultSet.getString("extensaofotouser"));
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
			
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

		String sql = "delete from model_login where id = ? and useradmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(idUser));

		statement.executeUpdate();
		connection.commit();
	}

}
