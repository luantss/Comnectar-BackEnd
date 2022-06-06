package com.generation.comnectar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.comnectar.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
		public List<Categoria> findAllByClasseCategoriaContainingIgnoreCase (String classeCategoria);
		public List<Categoria> findByModProdCategoriaContainingIgnoreCase (String modProdCategoria);
		public List<Categoria> findByFrescorCategoriaContainingIgnoreCase (boolean frescorCategoria);

}