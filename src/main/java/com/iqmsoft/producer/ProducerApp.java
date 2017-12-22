package com.iqmsoft.producer;

import javax.jms.ConnectionFactory;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import com.iqmsoft.common.Email;
import com.iqmsoft.common.MessageConverters;


@SpringBootApplication
public class ProducerApp implements CommandLineRunner {

	ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new PooledConnectionFactory("tcp://localhost:61616");
		return connectionFactory;
	}

	// custom JmsTemplate
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate(connectionFactory());
		template.setMessageConverter(MessageConverters.defaultMessageConverter());
		return template;
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(ProducerApp.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
		System.out.println("Producer Message sent!");
	}

	public void run(String... args) throws Exception {
	}
}
