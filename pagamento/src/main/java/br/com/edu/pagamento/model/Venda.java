package br.com.edu.pagamento.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.edu.pagamento.vo.VendaVO;
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
@Table(name="venda")
public class Venda implements Serializable
{	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "data", nullable = false)
	private Date data;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", 
			cascade = CascadeType.REFRESH)
	private List<ProdutoVenda> produtos;
	
	@Column(name = "valor", nullable = false, length = 10)
	private Double valor;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Date getData() {return data;}
	public void setData(Date data) {this.data = data;}

	public List<ProdutoVenda> getProdutos() {return produtos;}
	public void setProdutos(List<ProdutoVenda> produtos) 
	{this.produtos = produtos;}

	public Double getValor() {return valor;}
	public void setValor(Double valor) {this.valor = valor;}
	
	public static Venda convert(VendaVO vendaVO)
	{return new ModelMapper().map(vendaVO, Venda.class);}
}
