package com.project.number.consumer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrimeNumberConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeNumberConsumerService.class);

    @Autowired
    private CsvWriterService csvWriterService;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(Integer number) {
        LOGGER.info("Number consumed: " + number);
        if (isPrimeNumber(number)) {
            csvWriterService.writeRow(number);
            LOGGER.info("Prime number found: " + number);
        }
    }

    public static boolean isPrimeNumber(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
