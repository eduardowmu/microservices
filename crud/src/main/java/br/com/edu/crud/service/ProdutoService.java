package br.com.edu.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edu.crud.repository.ProdutoRepository;
import br.com.edu.crud.vo.ProdutoVO;

@Service
public class ProdutoService 
{	final ProdutoRepository repository;
	
	@Autowired 
	public ProdutoService(ProdutoRepository repository) 
	{this.repository = repository;}
	
	public ProdutoVO create(ProdutoVO produtoVO)
	{	
		return produtoVO;
	}
}
