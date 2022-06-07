package com.generation.comnectar.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.comnectar.model.Produto;
import com.generation.comnectar.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins="*", allowedHeaders="*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscaProdutos (){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscaProdutoById(@PathVariable Long id){
		return produtoRepository.findById(id).map(produto->ResponseEntity.ok(produto))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> buscaProdutosByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findByNomeProdutoContainingIgnoreCase(nome));
	}
	
	@GetMapping("/menorPreco/{preco}")
	public ResponseEntity<List<Produto>> buscaProdutosByMenorPreco(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoProdutoLessThan(preco));
	}
	
	@GetMapping("/maiorPreco/{preco}")
	public ResponseEntity<List<Produto>> buscaProdutosByMaiorPreco(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoProdutoGreaterThan(preco));
	}
	
	
}
