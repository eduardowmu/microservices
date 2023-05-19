package br.com.edu.crud.service;

import br.com.edu.crud.exception.ResourceNotFoundException;
import br.com.edu.crud.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.edu.crud.repository.ProdutoRepository;
import br.com.edu.crud.vo.ProdutoVO;

import java.util.Optional;

@Service
public class ProdutoService 
{	final ProdutoRepository repository;
	
	@Autowired 
	public ProdutoService(ProdutoRepository repository) 
	{this.repository = repository;}
	
	public ProdutoVO create(ProdutoVO produtoVO) {
		return ProdutoVO.create(this.repository.save(Produto.create(produtoVO)));
	}

	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = this.repository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}

	public ProdutoVO findById(Long id) {
		return ProdutoVO.create(this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado")));
	}

	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> optionalProduto = this.repository.findById(produtoVO.getId());
		if(!optionalProduto.isPresent()) {
			new ResourceNotFoundException("Produto nao encontrado");
		}

		return ProdutoVO.create(this.repository.save(Produto.create(produtoVO)));
	}

	public void delete(Long id) {
		var entity = this.repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

		this.repository.delete(entity);
	}

	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}
}
