package br.com.edu.pagamento.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.edu.pagamento.model.Produto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id", "estoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor//não chama a super classe
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO  extends RepresentationModel<ProdutoVO>
		implements Serializable
{	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("estoque")
	private Integer estoque;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Integer getEstoque() {return estoque;}
	public void setEstoque(Integer estoque) {this.estoque = estoque;}
	
	public static ProdutoVO convert(Produto produto)
	{return new ModelMapper().map(produto, ProdutoVO.class);}
}
