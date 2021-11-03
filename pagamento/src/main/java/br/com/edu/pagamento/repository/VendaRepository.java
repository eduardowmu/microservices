package br.com.edu.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edu.pagamento.model.Venda;
@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {}
