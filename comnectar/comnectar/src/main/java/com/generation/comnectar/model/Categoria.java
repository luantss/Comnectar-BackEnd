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
	//@Column(name="classe_categoria") - Para modificar o nome no banco de dados
	private String classeCategoria;
	
	//Opções: Familiar,Agroecológica,Orgânica,Sintrópica;
	@NotNull
	private String modprodCategoria;
	
	@NotNull
	private boolean frescorCategoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClasseCategoria() {
		return classeCategoria;
	}

	public void setClasseCategoria(String classeCategoria) {
		this.classeCategoria = classeCategoria;
	}

	public String getModprodCategoria() {
		return modprodCategoria;
	}

	public void setModprodCategoria(String modprodCategoria) {
		this.modprodCategoria = modprodCategoria;
	}

	public boolean isFrescorCategoria() {
		return frescorCategoria;
	}

	public void setFrescorCategoria(boolean frescorCategoria) {
		this.frescorCategoria = frescorCategoria;
	}

}
