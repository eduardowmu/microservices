package br.com.edu.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edu.pagamento.model.ProdutoVenda;
@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {}
