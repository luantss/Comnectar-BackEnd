package com.generation.comnectar.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	private enum Classe_categoria{
		Frutas,Verduras,Legumes;
	}
	
	@NotNull
    @Enumerated(EnumType.STRING)
    private Classe_categoria classe_categoria;
	
	private enum Modprod_categoria{
		Familiar,Agroecológica,Orgânica,Sintrópica;
	}
	
	@NotNull
    @Enumerated(EnumType.STRING)
    private Modprod_categoria modprod_categoria;
	
	@NotNull
	private boolean frescor_produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFrescor_produto() {
		return frescor_produto;
	}

	public void setFrescor_produto(boolean frescor_produto) {
		this.frescor_produto = frescor_produto;
	}

	public Classe_categoria getClasse_categoria() {
		return classe_categoria;
	}

	public void setClasse_categoria(Classe_categoria classe_categoria) {
		this.classe_categoria = classe_categoria;
	}

	public Modprod_categoria getModprod_categoria() {
		return modprod_categoria;
	}

	public void setModprod_categoria(Modprod_categoria modprod_categoria) {
		this.modprod_categoria = modprod_categoria;
	}
	
}
