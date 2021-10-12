package br.com.edu.crud.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.edu.crud.model.Produto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@AllArgsConstructor//não chama a super classe
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO extends RepresentationModel<ProdutoVO> 
						implements Serializable
{	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("estoque")
	private Integer estoque;
	
	@JsonProperty("preco")
	private Double preco;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}


	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public Integer getEstoque() {return estoque;}
	public void setEstoque(Integer estoque) {this.estoque = estoque;}
	
	public Double getPreco() {return preco;}
	public void setPreco(Double preco) {this.preco = preco;}
	
	public static ProdutoVO create(Produto produto)
	{return new ModelMapper().map(produto, ProdutoVO.class);}
	
	@Override public int hashCode() 
	{	final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		return result;
	}
	
	@Override public boolean equals(Object obj) 
	{	if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVO other = (ProdutoVO) obj;
		if (estoque == null) 
		{	if (other.estoque != null)
				return false;
		} 
		else if (!estoque.equals(other.estoque))
			return false;
		if (id == null) 
		{	if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} 
		else if (!nome.equals(other.nome))
			return false;
		if (preco == null) 
		{	if (other.preco != null)
				return false;
		} 
		else if (!preco.equals(other.preco))
			return false;
		return true;
	}
}
