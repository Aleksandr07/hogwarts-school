package ru.school.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Value("${server.port}")
    private int port;

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    public Integer getPort() {
        logger.info("Was invoked method for get port");
        return port;
    }

}
