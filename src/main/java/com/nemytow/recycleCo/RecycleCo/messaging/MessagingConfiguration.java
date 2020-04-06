package com.nemytow.recycleCo.RecycleCo.messaging;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class MessagingConfiguration {

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config) throws Exception {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.getRabbitConnectionFactory().setUri("amqp://hinsveto:LghUdm-3gLMz5nrL3AdtclpgV54Gdwn9@kangaroo.rmq.cloudamqp.com/hinsveto");

        return connectionFactory;

    }

}