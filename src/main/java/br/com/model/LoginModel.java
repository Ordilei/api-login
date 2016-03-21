package br.com.model;

public class LoginModel {
	
    private String nome;
    private String passwd;

    public LoginModel(String nome, String passwd) {
        this.nome = nome;
        this.passwd = passwd;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
