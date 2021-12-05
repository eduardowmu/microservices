package br.com.edu.pagamento.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.edu.pagamento.model.ProdutoVenda;
import br.com.edu.pagamento.model.Venda;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder(
		{"id", 
		 "data",
		 "produtos",
		 "valor"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor//não chama a super classe
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> 
		implements Serializable
{	private static final long serialVersionUID = 1L;	
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("data")
	private Date data;
	
	@JsonProperty("produtos")
	private List<ProdutoVendaVO> produtos;
	
	@JsonProperty("valor")
	private Double valor;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Date getData() {return data;}
	public void setData(Date data) {this.data = data;}
	
	public List<ProdutoVendaVO> getProdutos() 
	{return produtos;}
	public void setProdutos(List<ProdutoVendaVO> produtos) 
	{this.produtos = produtos;}

	public Double getValor() {return valor;}
	public void setValor(Double valor) {this.valor = valor;}
	
	public static VendaVO convert(Venda venda)
	{return new ModelMapper().map(venda, VendaVO.class);}
}
