package com.generation.comnectar.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.comnectar.model.Categoria;
import com.generation.comnectar.model.Produto;
import com.generation.comnectar.model.Usuario;
import com.generation.comnectar.repository.CategoriaRepository;
import com.generation.comnectar.repository.ProdutoRepository;
import com.generation.comnectar.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoControllerTest {
  
  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private TestRestTemplate testRestTemplate;
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @BeforeAll
  void start(){
    produtoRepository.deleteAll();
    var v = new BigDecimal(10);
    var v2 = new BigDecimal(10);
    var usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    var categoria = categoriaRepository.save(  new Categoria(0L,"Frutas","Sintróprica",true,null));
    
    var produto = new Produto(0L,"nomeProduto","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   var produto2 = new Produto(0L,"maçã","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   produtoRepository.save(produto);
   produtoRepository.save(produto2);
  }

  @Order(1)
  @Test
  @DisplayName("Deve cadastrar um produto")
  public void cadastrarProduto (){
    BigDecimal v = new BigDecimal(10);
    BigDecimal v2 = new BigDecimal(10);
    Usuario usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    Categoria categoria = categoriaRepository.save(  new Categoria(0L,"Frutas","Sintróprica",true,null));
    
    HttpEntity<Produto> requisicao = new HttpEntity<Produto>(
      new Produto(0L,"nomeProduto","fotoProduto",
     "infoProduto",
      v,"kilo",
      v2,  "String chegadaProduto",
    "String shelfProduto",categoria,
    usuario
    ));

    ResponseEntity<Produto> resposta = testRestTemplate.withBasicAuth("root", "root").exchange(
      "/produtos", 
      HttpMethod.POST, 
      requisicao, 
      Produto.class);

      assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
      assertEquals(produtoRepository.findById(resposta.getBody().getId())
      .get().getNomeProduto(),resposta.getBody().getNomeProduto());
  }

  @Order(2)
  @Test
  @DisplayName("deve buscar todos os produtos")
  public void listarProdutos(){
   

   ResponseEntity<String> resposta = testRestTemplate.withBasicAuth(
    "root", "root").exchange(
      "/produtos", 
      HttpMethod.GET, 
      null
      ,String.class
      );

      assertEquals(HttpStatus.OK, resposta.getStatusCode());
  }
  @Order(3)
  @Test
  @DisplayName("Deve retornar um produto por id")
  public void retornarPorId(){
    var v = new BigDecimal(10);
    var v2 = new BigDecimal(10);
    var usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    var categoria = categoriaRepository.save(  new Categoria(0L,"Frutas","Sintróprica",true,null));
    
    var produto = new Produto(0L,"nomeProduto","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   var produto2 = new Produto(0L,"maçã","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   produto = produtoRepository.save(produto);
   produto2 = produtoRepository.save(produto2);
  
   ResponseEntity<Produto> resposta = testRestTemplate.withBasicAuth("root", "root").exchange(
    "/produtos/"+produto.getId(), 
    HttpMethod.GET, 
  null, 
    Produto.class);
    ResponseEntity<Produto> resposta2 = testRestTemplate.withBasicAuth("root", "root").exchange(
    "/produtos/"+produto2.getId(), 
    HttpMethod.GET, 
  null, 
    Produto.class);


    assertEquals(HttpStatus.OK, resposta.getStatusCode());
    assertEquals(produto.getNomeProduto(), resposta.getBody().getNomeProduto());
    assertEquals(HttpStatus.OK, resposta2.getStatusCode());
    assertEquals(produto2.getNomeProduto(), resposta2.getBody().getNomeProduto());
  }
  @Order(4)
  @Test
  @DisplayName("deve atualizar um produto:")
  public void atualizaProduto(){
    var v = new BigDecimal(10);
    var v2 = new BigDecimal(10);
    var usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    var categoria = categoriaRepository.save(  new Categoria(0L,"Frutas","Sintróprica",true,null));
    
    var produto = new Produto(0L,"nomeProduto","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   
   produto = produtoRepository.save(produto);


   var requisicao = new HttpEntity<Produto>(new Produto(produto.getId(),"maçã","fotoProduto",
   "infoProduto",
    v,"kilo",
    v2,  "String chegadaProduto",
  "String shelfProduto",categoria,
  usuario
  ));

   ResponseEntity<Produto> resposta = testRestTemplate.withBasicAuth("root", "root").exchange(
    "/produtos/"+produto.getId(), 
    HttpMethod.PUT, 
  requisicao, 
    Produto.class);

    assertEquals(HttpStatus.OK, resposta.getStatusCode());
    assertEquals(produtoRepository.findById(
      produto.getId()).get().getNomeProduto(), "maçã");
  }
  @Order(5)
  @Test
  @DisplayName("deve deletar um produto")
  public void deletarProduto (){
    var v = new BigDecimal(10);
    var v2 = new BigDecimal(10);
    var usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    var categoria = categoriaRepository.save(  new Categoria(0L,"Frutas","Sintróprica",true,null));
    
    var produto = new Produto(0L,"nomeProduto","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   
   produto = produtoRepository.save(produto);

   ResponseEntity<Void> resposta = testRestTemplate.withBasicAuth("root", "root").exchange(
    "/produtos/"+produto.getId(), 
    HttpMethod.DELETE, 
    null,
    Void.class);

    assertEquals(HttpStatus.OK, resposta.getStatusCode());
    assertEquals(produtoRepository.findById(produto.getId()), Optional.empty());
  }

  @Order(6)
  @Test
  @DisplayName("deve retornar produto pelo nome")
  public void retornarPorNome (){
    var v = new BigDecimal(10);
    var v2 = new BigDecimal(10);
    var usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    var categoria = categoriaRepository.save(  new Categoria(0L,"Frutas","Sintróprica",true,null));
    
    var produto = new Produto(0L,"nomeProduto","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario
   );
   
   produto = produtoRepository.save(produto);

   ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root", "root").exchange(
    "/produtos/nome/"+produto.getNomeProduto(), 
    HttpMethod.GET, 
    null,
    String.class);

    assertEquals(HttpStatus.OK, resposta.getStatusCode());

  }


}
