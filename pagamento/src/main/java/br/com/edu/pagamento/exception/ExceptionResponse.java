package br.com.edu.pagamento.exception;

import java.io.Serializable;
import java.util.Date;

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
public class ExceptionResponse implements Serializable
{	private static final long serialVersionUID = 1L;	

	/*Momento em que o erro ocorreu*/
	private Date timeStamp;
	
	private String mensagem;
	
	private String details;
}