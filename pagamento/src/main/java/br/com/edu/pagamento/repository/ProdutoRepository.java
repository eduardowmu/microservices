package br.com.edu.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edu.pagamento.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
