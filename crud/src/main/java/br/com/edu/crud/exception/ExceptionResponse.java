package br.com.edu.crud.exception;

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
	
	
	public ExceptionResponse() {}

	public ExceptionResponse(Date timeStamp, 
			String mensagem, String details) 
	{	this.timeStamp = timeStamp;
		this.mensagem = mensagem;
		this.details = details;
	}
	
	public Date getTimeStamp() {return timeStamp;}
	public void setTimeStamp(Date timeStamp) {this.timeStamp = timeStamp;}

	public String getMensagem() {return mensagem;}
	public void setMensagem(String mensagem) {this.mensagem = mensagem;}

	public String getDetails() {return details;}
	public void setDetails(String details) {this.details = details;}
}
