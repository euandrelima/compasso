package com.compasso.clientcity.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sexo")
	@Enumerated(value = EnumType.STRING)
	private Sexo sexo;
	
//	@Column(name = "dt_nascimento")
//	private LocalDate dtNascimento;
	
	@Column(name = "idade")
	private Long idade;
	
	Cliente() {	}
	
	Cliente(String nome) {
		this.nome = nome;
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

//	public LocalDate getDtNascimento() {
//		return dtNascimento;
//	}
//
//	public void setDtNascimento(LocalDate dtNascimento) {
//		this.dtNascimento = dtNascimento;
//	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}
	



	
}