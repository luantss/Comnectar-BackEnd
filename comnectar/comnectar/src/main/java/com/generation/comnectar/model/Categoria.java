package com.generation.comnectar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//Opções: Frutas,Verduras,Legumes;
	@NotNull
	private String classe_categoria;
	
	//Opções: Familiar,Agroecológica,Orgânica,Sintrópica;
	@NotNull
	private String modprod_categoria;
	
	@NotNull
	private boolean frescor_categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFrescor_categoria() {
		return frescor_categoria;
	}

	public void setFrescor_categoria(boolean frescor_categoria) {
		this.frescor_categoria = frescor_categoria;
	}

	public String getClasse_categoria() {
		return classe_categoria;
	}

	public void setClasse_categoria(String classe_categoria) {
		this.classe_categoria = classe_categoria;
	}

	public String getModprod_categoria() {
		return modprod_categoria;
	}

	public void setModprod_categoria(String modprod_categoria) {
		this.modprod_categoria = modprod_categoria;
	}
}
