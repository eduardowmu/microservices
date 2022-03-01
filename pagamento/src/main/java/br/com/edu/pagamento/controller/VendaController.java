package br.com.edu.pagamento.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.pagamento.model.Venda;
import br.com.edu.pagamento.service.VendaService;
import br.com.edu.pagamento.vo.VendaVO;

@RestController
@RequestMapping("/venda")
public class VendaController 
{	@Value("${server.port}")
	String port;
	
	private final VendaService service;
	private final PagedResourcesAssembler<VendaVO> assembler;
	
	@Autowired
	public VendaController(VendaService service, PagedResourcesAssembler<VendaVO> assembler) 
	{	this.service = service;
		this.assembler = assembler;
	}
	
	@RequestMapping("/mostrarPorta")
	public String showGate() {return port;}
	
	@GetMapping(value = "/{id}", produces = { "application/json", 
			"application/xml", "application/x-yaml" })
	public VendaVO findById(@PathVariable("id") Long id)	
	{	VendaVO vo = this.service.findById(id);
		vo.add(linkTo(methodOn(VendaController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@GetMapping(produces = { "application/json", 
			"application/xml", "application/x-yaml" })
	public List<VendaVO> findAll()
	{	List<VendaVO> vendas = this.service.findAll();
		if(vendas != null)
		{	vendas.stream().forEach(v -> v.add(linkTo(methodOn(VendaController.class)
				.findById(v.getId())).withSelfRel()));
		}
		return vendas;
	}
	
	@PostMapping(
			produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = {"application/json", "application/xml", "application/x-yaml" })
	public VendaVO create(@RequestBody VendaVO vendaVO)
	{	VendaVO vo = this.service.create(vendaVO);
		vo.add(linkTo(methodOn(VendaController.class)
				.findById(vo.getId())).withSelfRel());
		return vo;
	}
}
