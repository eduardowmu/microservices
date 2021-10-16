package br.com.edu.pagamento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edu.pagamento.exception.ResourceNotFoundException;
import br.com.edu.pagamento.model.ProdutoVenda;
import br.com.edu.pagamento.model.Venda;
import br.com.edu.pagamento.repository.ProdutoVendaRepository;
import br.com.edu.pagamento.repository.VendaRepository;
import br.com.edu.pagamento.vo.ProdutoVO;
import br.com.edu.pagamento.vo.VendaVO;

@Service
public class VendaService 
{	private final VendaRepository repository;
	private final ProdutoVendaRepository pvRepository;
	
	@Autowired
	public VendaService(VendaRepository repository, 
			ProdutoVendaRepository pvRepository) 
	{	this.repository = repository;
		this.pvRepository = pvRepository;
	}
	
	public VendaVO create(VendaVO vendaVO)
	{	Venda venda = this.repository.save(Venda.convert(vendaVO));
		
		/*Não devemos esquecer que uma venda está atrelada a um produto*/
		List<ProdutoVenda> produtosVendidos = new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.convert(p);
			pv.setVenda(venda);
			produtosVendidos.add(this.pvRepository.save(pv));
		});
		
		venda.setProdutos(produtosVendidos);
		
		return VendaVO.convert(venda);
	}
	
	public List<VendaVO> findAll()
	{	List<VendaVO> vendas = new ArrayList<>();
		List<Venda> listaVendas = this.repository.findAll();
		if(listaVendas != null)
		{listaVendas.stream().forEach(v -> vendas.add(VendaVO.convert(v)));}
		return vendas;
	}
	
	public VendaVO findById(Long id)
	{	return VendaVO.convert(this.repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
					"No records found for this ID")));
	}
}
