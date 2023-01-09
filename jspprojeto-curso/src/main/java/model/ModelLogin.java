package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String password;
	private boolean useradmin;
	private String perfil;
	private String sexo;
	
	private Date dataNascimento;
	
	private Double rendaMensal;
	
	private List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();

	/* upload de imagem */

	private String fotouser;

	private String extensaofotouser;

	/* dados do cep */

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String numero;
	
	
	public void setTelefones(List<ModelTelefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<ModelTelefone> getTelefones() {
		return telefones;
	}
	
	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFotouser() {
		return fotouser;
	}

	public void setFotouser(String fotouser) {
		this.fotouser = fotouser;
	}

	public String getExtensaofotouser() {
		return extensaofotouser;
	}

	public void setExtensaofotouser(String extensaofotouser) {
		this.extensaofotouser = extensaofotouser;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}

	// saber se é u novo registro
	public boolean isNovo() {
		if (this.id == null) {
			return true;
		} else if (this.id != null && this.id > 0) {
			return false;
		}
		return id == null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ModelLogin [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", password="
				+ password + ", useradmin=" + useradmin + ", perfil=" + perfil + ", sexo=" + sexo + ", dataNascimento="
				+ dataNascimento + ", rendaMensal=" + rendaMensal + ", fotouser=" + fotouser + ", extensaofotouser="
				+ extensaofotouser + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro
				+ ", localidade=" + localidade + ", uf=" + uf + ", numero=" + numero + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, nome, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelLogin other = (ModelLogin) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password);
	}
	
	public String getMostrarTelefoneRel() {
		String fone = "Telefone:\n";
		for(ModelTelefone modelTelefone :telefones) {
			fone += modelTelefone.getNumero()+"\n";
		}
		return fone;
	}

}
