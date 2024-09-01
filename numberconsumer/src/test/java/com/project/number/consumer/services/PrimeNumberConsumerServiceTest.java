package com.project.number.consumer.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberConsumerServiceTest {

    @InjectMocks
    private PrimeNumberConsumerService primeNumberConsumerService;

    @ParameterizedTest(name = "Test if {0} is prime")
    @CsvSource({
            "2, true",
            "3, true",
            "4, false",
            "5, true",
            "6, false",
            "7, true",
            "8, false",
            "9, false",
            "10, false",
            "11, true",
            "12, false",
            "13, true",
            "7919, true",  // Large prime number
            "8000, false"  // Large non-prime number
    })
    void testIsPrime(int number, boolean expected) {
        boolean actual = PrimeNumberConsumerService.isPrimeNumber(number);
        if (expected) {
            assertTrue(actual, number + " should be prime");
        } else {
            assertFalse(actual, number + " should not be prime");
        }
    }
}