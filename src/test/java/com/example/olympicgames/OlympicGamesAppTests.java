package com.example.olympicgames;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class OlympicGamesAppTests {

    @Autowired
    private DullRepository repo;

    @Test
    void ping() {
        repo.ping();
    }

    @Test
    void task1() {
        List<?> result = repo.query1();
        System.out.println(byLines(result.stream()));
    }

    @Test
    void task2() {
        List<?> result = repo.query2();
        System.out.println(byLines(result.stream()));
    }

    @Test
    void task3() {
        List<?> result = repo.query3();
        System.out.println(byLines(result.stream()));
    }

    @Test
    void task4() {
        List<?> result = repo.query4();
        System.out.println(byLines(result.stream()));
    }

    @Test
    void task5() {
        List<?> result = repo.query5();
        System.out.println(byLines(result.stream()));
    }

    private <T> String byLines(Stream<T> stream) {
        return stream.map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
