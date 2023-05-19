package br.com.edu.crud.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.edu.crud.model.Produto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

/*Essa notação que irá definir a órdem
 *que os atributos irão retornar assim
 *que uma requisição for realizada*/
@JsonPropertyOrder(
		{"id", 
		 "nome",
		 "estoque",
		 "preco"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable
{	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("estoque")
	private Integer estoque;
	
	@JsonProperty("preco")
	private Double preco;
	
	public static ProdutoVO create(Produto produto)
	{return new ModelMapper().map(produto, ProdutoVO.class);}
}
