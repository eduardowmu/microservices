package br.com.edu.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.edu.crud.exception.ResourceNotFoundException;
import br.com.edu.crud.model.Produto;
import br.com.edu.crud.repository.ProdutoRepository;
import br.com.edu.crud.vo.ProdutoVO;

@Service
public class ProdutoService 
{	final ProdutoRepository repository;
	
	@Autowired 
	public ProdutoService(ProdutoRepository repository) 
	{this.repository = repository;}
	
	public ProdutoVO create(ProdutoVO produtoVO)
	{	ProdutoVO pVO = ProdutoVO.create(this.repository.save(
			Produto.create(produtoVO)));
		return pVO;
	}
	
	public List<ProdutoVO> findAll(/* Pageable pageable */)
	{	//var page = this.repository.findAll(pageable);
		/*método que irá converter cada objeto de página
		 *em ProdutoVO*/
		List<ProdutoVO> produtosVO = new ArrayList<>();
		List<Produto> produtos = this.repository.findAll();
		if(!produtos.isEmpty())
		{	for(Produto p:produtos)
			{produtosVO.add(ProdutoVO.create(p));}
		}
		return produtosVO;
				//page.map(this::convertToProdutoVO);
	}
	
	/*
	 * private ProdutoVO convertToProdutoVO(Produto produto) {return
	 * ProdutoVO.create(produto);}
	 */
	
	public ProdutoVO findById(Long id) 
	{	var produto = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Produto não encontrado"));
		return ProdutoVO.create(produto);
	}
	
	public ProdutoVO update(ProdutoVO produtoVO)
	{	final Optional<Produto> optionalProduto = 
					this.repository.findById(produtoVO.getId());
		
		/*caso nao seja encontrado*/
		if(!optionalProduto.isPresent())
		{new ResourceNotFoundException("Produto não encontrado");}
		
		return ProdutoVO.create(this.repository.save(
				Produto.create(produtoVO)));
	}
	
	public void delete(Long id) 
	{	var produto = this.repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
					"Produto não encontrado"));
		
		this.repository.delete(produto);
	}
}
