package br.com.edu.pagamento.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.edu.pagamento.model.ProdutoVenda;
import br.com.edu.pagamento.model.Venda;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@JsonPropertyOrder(
		{"id", 
		 "idProduto",
		 "quantidade"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor//não chama a super classe
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO>
	implements Serializable 
{	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("idProduto")
	private Long idProduto;
	
	@JsonProperty("quantidade")
	private Integer quantidade;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Long getIdProduto() {return idProduto;}
	public void setIdProduto(Long idProduto) {this.idProduto = idProduto;}

	public Integer getQuantidade() {return quantidade;}
	public void setQuantidade(Integer quantidade) 
	{this.quantidade = quantidade;}
	
	public static ProdutoVendaVO convert(ProdutoVenda produtoVenda)
	{return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);}
}
