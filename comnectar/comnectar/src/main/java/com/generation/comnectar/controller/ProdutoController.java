package com.generation.comnectar.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return ResponseEntity.ok(produtoRepository.findByPrecoProdutoLessThanEqual(preco));
	}
	
	@GetMapping("/maiorPreco/{preco}")
	public ResponseEntity<List<Produto>> buscaProdutosByMaiorPreco(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoProdutoGreaterThanEqual(preco));
	}
	
	@PostMapping
	public ResponseEntity<Produto> adicionaProduto (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<Produto> atualizaProduto(@Valid @RequestBody Produto produto, @PathVariable Long id){
		return ResponseEntity.ok(produtoRepository.save(produto));
	}
	
	@DeleteMapping ("/{id}")
	public void deleteProduto (@PathVariable Long id) {
		produtoRepository.deleteById(id);
	}
}