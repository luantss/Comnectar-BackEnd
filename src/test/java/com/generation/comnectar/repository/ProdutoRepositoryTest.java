package com.generation.comnectar.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.comnectar.model.Categoria;
import com.generation.comnectar.model.Produto;
import com.generation.comnectar.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoRepositoryTest {

  @Autowired
  private ProdutoRepository produtoRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private CategoriaRepository categoriaRepository;

  private Produto produto;
  private Produto produto2;

  @BeforeAll
  void start(){
    produtoRepository.deleteAll();
    var v = new BigDecimal(10);
    var v2 = new BigDecimal(10);

    var usuario = usuarioRepository.save(new Usuario(0L,"lairton","lairton@gmail.com","123456789", "Floresta-PE", "fdsajfadsjfdsa",
    null));
    var categoria = categoriaRepository.save( new Categoria(0L,"Frutas","Sintróprica",true,null));

    this.produto = produtoRepository.save(new Produto(0L,"banana","fotoProduto",
    "infoProduto",
     v,"kilo",
     v2,  "String chegadaProduto",
   "String shelfProduto",categoria,
   usuario));

   this.produto2 = produtoRepository.save(new Produto(0L,"banana Prata","fotoProduto",
   "kkkkkkkkkkkk",
    v,"kilo",
    v2,  "String chegadaProduto",
  "String shelfProduto",categoria,
  usuario));

  }

  @Test
  @DisplayName("deve retornar um produto")
  public void deveRetornarUmUsuario(){
    Optional<Produto> produto1 = produtoRepository.findById(this.produto.getId());

    assertTrue(produto1.get().getNomeProduto().equals("banana"));
  }

  @Test
  @DisplayName("deve retornar 2 Produtos")
  public void deveRetornarDoisProdutos(){
    List<Produto> produtos = produtoRepository
      .findByNomeProdutoContainingIgnoreCase("banana");

    assertEquals(2, produtos.size());
    assertTrue(produtos.get(0).getInfoProduto()
      .equals("infoProduto"));

    assertTrue(produtos.get(1).getInfoProduto()
      .equals("kkkkkkkkkkkk"));
}
}
