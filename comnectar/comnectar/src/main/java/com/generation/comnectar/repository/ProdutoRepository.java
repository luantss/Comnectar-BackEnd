package com.generation.comnectar.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.comnectar.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	
	public List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);
	public List<Produto> findByPrecoProdutoLessThanEqual(BigDecimal precoProduto);
	public List<Produto> findByPrecoProdutoGreaterThanEqual(BigDecimal precoProduto);
}