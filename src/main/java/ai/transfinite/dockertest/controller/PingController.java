package ai.transfinite.dockertest.controller;

import io.transfinite.collectors.CustomCollectors;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);

    @GetMapping(path = "/ping")
    public String ping() {
        LOGGER.info("Received ping request");
        return "Pong";
    }

    @PostMapping(path = "/collect")
    public String collect(@RequestBody TokenRequest request) {
        List<String> tokens = request.tokens();
        LOGGER.info("Received collect request with {} tokens", tokens.size());
        LOGGER.debug("Tokens: {}", tokens);

        List<String> collect = tokens.stream().collect(CustomCollectors.duplicateConsecutiveElements());
        LOGGER.info("Found {} duplicate tokens: {}", collect.size(), collect);

        return "Duplicate tokens: " + collect;
    }
}
