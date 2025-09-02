package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Stream;
@Service
public class InfoService {

    public String checkStreamIterator() {
        long before = System.currentTimeMillis();
        int sum = calcSum();
        long after = System.currentTimeMillis();

        long beforeImpr = System.currentTimeMillis();
        int sumImpr = calcSumImpr();
        long afterImpr = System.currentTimeMillis();

        return "Sum: " + sum + "; Time: " + (after - before) + "ms | " +
                "SumImpr: " + sumImpr + "; Time: " + (afterImpr - beforeImpr) + "ms";
    }

    private int calcSum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }

    private int calcSumImpr() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }

}