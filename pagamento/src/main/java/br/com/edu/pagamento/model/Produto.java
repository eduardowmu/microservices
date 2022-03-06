package br.com.edu.pagamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.edu.pagamento.vo.ProdutoVO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="produto")
public class Produto 
{	@Id
	private Long id;
	
	@Column(name = "estoque", nullable = false, length = 10)
	private Integer estoque;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Integer getEstoque() {return estoque;}
	public void setEstoque(Integer estoque) {this.estoque = estoque;}
	
	public static Produto convert(ProdutoVO produtoVO)
	{return new ModelMapper().map(produtoVO, Produto.class);}
}
