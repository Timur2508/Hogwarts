package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

@RestController
@Tag(name = "Контроллер по работе с портом")
public class InfoController {

    private final int port;
    private final InfoService infoService;
    public InfoController(@Value("${server.port}") int port, InfoService infoService) {
        this.port = port;
        this.infoService = infoService;
    }

    @GetMapping(path = "getPort")
    public int getPort() {
        return port;
    }

    @GetMapping
    public String checkStreamIterator() {
        return infoService.checkStreamIterator();
    }
}