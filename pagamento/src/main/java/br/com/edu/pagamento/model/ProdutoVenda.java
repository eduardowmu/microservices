package br.com.edu.pagamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.edu.pagamento.vo.ProdutoVendaVO;
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
@Table(name="produto_venda")
public class ProdutoVenda implements Serializable
{	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_produto", nullable = false, length = 10)
	private Long idProduto;
	
	@Column(name = "quantidade", nullable = false, length = 10)
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venda")
	private Venda venda;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Long getIdProduto() {return idProduto;}
	public void setIdProduto(Long idProduto) 
	{this.idProduto = idProduto;}
	
	public Integer getQuantidade() {return quantidade;}
	public void setQuantidade(Integer quantidade) 
	{this.quantidade = quantidade;}

	public Venda getVenda() {return venda;}
	public void setVenda(Venda venda) {this.venda = venda;}
	
	public static ProdutoVenda convert(ProdutoVendaVO produtoVendaVO)
	{return new ModelMapper().map(produtoVendaVO, ProdutoVenda.class);}
}
