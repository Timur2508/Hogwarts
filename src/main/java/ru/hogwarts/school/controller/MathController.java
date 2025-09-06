package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/calculate-sum")
    public long calculateOptimizedSum() {
        long n = 1_000_000;
        return n * (1 + n) / 2;
    }
}