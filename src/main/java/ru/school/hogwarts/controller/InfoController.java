package ru.school.hogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.school.hogwarts.service.InfoService;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/getPort")
    public ResponseEntity<Integer> getPort() {
        Integer port = infoService.getPort();
        return ResponseEntity.ok(port);
    }

    @GetMapping("get-int-value")
    public ResponseEntity<Integer> getIntValue() {
        return ResponseEntity.ok(infoService.getIntValue());
    }
}
