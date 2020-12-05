package dev.alexandreyoshimatsu.springtest.entrypoint;

import com.github.javafaker.Faker;
import dev.alexandreyoshimatsu.springtest.entrypoint.data.HelloWorldRequest;
import dev.alexandreyoshimatsu.springtest.entrypoint.data.HelloWorldResponse;
import dev.alexandreyoshimatsu.springtest.usecase.HelloWorldUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloWorldController {

    private final HelloWorldUseCase helloWorldUseCase;

    @GetMapping(path = "/helloworld")
    public ResponseEntity<HelloWorldResponse> getMessage() {

        log.debug("/helloworld");

        HelloWorldResponse helloWorldResponse = HelloWorldResponse.builder()
                .id(UUID.randomUUID().toString())
                .mensagem(helloWorldUseCase.printHello())
                .build();

        return new ResponseEntity<>(helloWorldResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/random/message")
    public ResponseEntity<HelloWorldResponse> getRandomMessage() {

        log.debug("/random/message");

        Faker faker = new Faker();

        HelloWorldResponse helloWorldResponse = HelloWorldResponse.builder()
                .id(UUID.randomUUID().toString())
                .mensagem(faker.gameOfThrones().character())
                .build();

        return new ResponseEntity<>(helloWorldResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<HelloWorldResponse> getMessage(@RequestParam String message) {

        log.debug(message);

        HelloWorldResponse helloWorldResponse = HelloWorldResponse.builder()
                .id(UUID.randomUUID().toString())
                .mensagem(helloWorldUseCase.printMessage(message))
                .build();

        return new ResponseEntity<>(helloWorldResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/helloworld/{id}")
    public ResponseEntity<HelloWorldResponse> getMessageById(@PathVariable String id) {

        log.debug("HelloWorld");

        HelloWorldResponse helloWorldResponse = HelloWorldResponse.builder()
                .id(id)
                .mensagem("HelloWorld")
                .build();

        return new ResponseEntity<>(helloWorldResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<HelloWorldResponse> postMessage(@RequestBody HelloWorldRequest helloWorldRequest) {

        log.debug(helloWorldRequest.getMensagem());

        HelloWorldResponse helloWorldResponse = HelloWorldResponse.builder()
                .id(UUID.randomUUID().toString())
                .mensagem(helloWorldRequest.getMensagem())
                .password(helloWorldRequest.getPassword())
                .build();

        return new ResponseEntity<>(helloWorldResponse, HttpStatus.CREATED);
    }

}
