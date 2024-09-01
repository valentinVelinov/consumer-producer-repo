package com.project.numberproducer.services;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class RandomNumbersService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CsvWriterService csvWriterService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumbersService.class);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final Random random = new Random();

    @PostConstruct
    public void startProducing() {
        scheduler.scheduleAtFixedRate(this::generateAndSendNumbers, 0, 1, TimeUnit.SECONDS);
    }

    private void generateAndSendNumbers() {
        int numberOfMessages = random.nextInt(5) + 1;

        IntStream.range(0, numberOfMessages)
                .map(i -> random.nextInt(Integer.MAX_VALUE))
                .peek(randomNumber -> {
                    rabbitTemplate.convertAndSend(exchange, routingKey, randomNumber);
                    LOGGER.info("Number sent: " + randomNumber);
                })
                .forEach(csvWriterService::writeRow);
    }

}
