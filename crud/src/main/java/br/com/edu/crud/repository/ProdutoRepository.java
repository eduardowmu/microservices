package br.com.edu.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edu.crud.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
