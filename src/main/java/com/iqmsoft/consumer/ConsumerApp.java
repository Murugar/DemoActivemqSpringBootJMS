package com.iqmsoft.consumer;

import javax.jms.ConnectionFactory;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.iqmsoft.common.MessageConverters;


@SpringBootApplication(scanBasePackages = { "com.iqmsoft.consumer" })
@EnableJms
public class ConsumerApp {

	ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new PooledConnectionFactory("tcp://localhost:61616");
		return connectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setDestinationResolver(new DynamicDestinationResolver());
		factory.setMessageConverter(MessageConverters.defaultMessageConverter());
		factory.setConcurrency("3-10");
		return factory;
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ConsumerApp.class, args);
	}
}
