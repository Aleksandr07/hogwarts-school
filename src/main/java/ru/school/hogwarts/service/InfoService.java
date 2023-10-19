package ru.school.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class InfoService {

    @Value("${server.port}")
    private int port;

    private final Logger logger = LoggerFactory.getLogger(InfoService.class);

    public Integer getPort() {
        logger.info("Was invoked method for get port");
        return port;
    }

    public Integer getIntValue() {

        logger.info("Was invoked method for calc value with modified logic that returns the value in the least amount of time");
        int sumCycles = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            sumCycles += i;
        }

        return sumCycles;
    }
}
