package br.com.edu.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.edu.crud.service.ProdutoService;
import br.com.edu.crud.vo.ProdutoVO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	private final ProdutoService service;
	private final PagedResourcesAssembler<ProdutoVO> assembler;

	@Autowired
	public ProdutoController(ProdutoService service, PagedResourcesAssembler<ProdutoVO> assembler) {
		this.service = service;
		this.assembler = assembler;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", 
			"application/xml", "application/x-yaml" })
	public ProdutoVO findById(@PathVariable("id") Long id) {
		ProdutoVO produtoVO = this.service.findById(id);
		/* HATEOAS */
		produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
		return produtoVO;
	}

	/*
	 * @GetMapping(produces = {"application/json", "application/xml",
	 * "application/x-yaml"}) public ResponseEntity<?> findByAll(
	 * 
	 * @RequestParam(value = "page", defaultValue = "0") int page, Quantidade de
	 * registros por página
	 * 
	 * @RequestParam(value = "limit", defaultValue = "12") int limit,
	 * 
	 * @RequestParam(value = "direction", defaultValue = "asc") String direction) {
	 * Ternario que verifica se a lista será apresentada em órdem crescente ou não
	 * var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC :
	 * Direction.ASC;
	 * 
	 * Ordenação por nome do produto Pageable pageable = PageRequest.of(page, limit,
	 * Sort.by( direction, "nome"));
	 * 
	 * Page<ProdutoVO> produtos = this.service.findAll(pageable);
	 * 
	 * //HATEOAS produtos.stream().forEach(p ->
	 * p.add(linkTo(methodOn(ProdutoController.class)
	 * .findById(p.getId())).withSelfRel()));
	 * 
	 * PagedModel<EntityModel<ProdutoVO>> pageModel = assembler.toModel(produtos);
	 * 
	 * return new ResponseEntity<>(pageModel, HttpStatus.OK); }
	 */
	
	@GetMapping(produces = {"application/json", "application/xml",
			 "application/x-yaml"}) 
	public List<ProdutoVO> findByAll()
	{	List<ProdutoVO> produtos = this.service.findAll();
		produtos.stream().forEach(p -> p.add(linkTo(methodOn(ProdutoController.class)
				.findById(p.getId())).withSelfRel()));
		return produtos;
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ProdutoVO create(@RequestBody ProdutoVO produtoVO) {
		ProdutoVO pVO = this.service.create(produtoVO);
		pVO.add(linkTo(methodOn(ProdutoController.class).findById(pVO.getId())).withSelfRel());
		return pVO;
	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ProdutoVO update(@RequestBody ProdutoVO produtoVO) {
		ProdutoVO pVO = this.service.update(produtoVO);
		pVO.add(linkTo(methodOn(ProdutoController.class).findById(pVO.getId())).withSelfRel());
		return pVO;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}
}
