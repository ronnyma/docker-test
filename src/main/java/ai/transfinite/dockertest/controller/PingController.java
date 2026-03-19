package ai.transfinite.dockertest.controller;

import io.transfinite.collectors.CustomCollectors;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {
    @GetMapping(path = "/ping")
    public String ping() {
        return "Pong";
    }

    @PostMapping(path = "/collect")
    public String collect(@RequestBody TokenRequest request) {
        List<String> tokens = request.tokens();
        List<String> collect = tokens.stream().collect(CustomCollectors.duplicateConsecutiveElements());

        return "Duplicate consecutive tokens: " + collect;
    }
}
