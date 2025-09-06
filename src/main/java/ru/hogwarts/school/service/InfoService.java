package ru.hogwarts.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.school.model.Faculty;

import java.util.Comparator;
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

    @GetMapping("/longest-name")
    public String getLongestFacultyName(FacultyService facultyService) {
        return facultyService.getAllFaculties().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No faculties found"));
    }
}