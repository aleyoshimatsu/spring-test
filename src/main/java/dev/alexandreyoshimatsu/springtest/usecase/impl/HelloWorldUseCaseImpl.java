package dev.alexandreyoshimatsu.springtest.usecase.impl;

import dev.alexandreyoshimatsu.springtest.usecase.HelloWorldUseCase;
import dev.alexandreyoshimatsu.springtest.usecase.entity.HelloWorldEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloWorldUseCaseImpl implements HelloWorldUseCase {

    @Override
    public String printHello() {
        return printMessage("Hello World!");
    }

    @Override
    public String printMessage(String message) {
        return message;
    }

    public void saveHelloWorld(HelloWorldEntity helloWorldEntity) {
        log.debug(helloWorldEntity.toString());
    }
}
