package br.com.edu.crud.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.edu.crud.vo.ProdutoVO;

@Component
public class ProdutoSendMessage 
{	@Value("${crud.rabbitmq.exchange}")
	String exchange;

	@Value("${crud.rabbitmq.routingkey}")
	String routingKey;
	
	public final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public ProdutoSendMessage(RabbitTemplate rabbitTemplate) 
	{this.rabbitTemplate = rabbitTemplate;}
	
	public void sendMessage(ProdutoVO produtoVO)
	{this.rabbitTemplate.convertAndSend(this.exchange, this.routingKey, produtoVO);}
}
