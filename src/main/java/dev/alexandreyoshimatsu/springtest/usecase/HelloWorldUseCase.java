package dev.alexandreyoshimatsu.springtest.usecase;

import dev.alexandreyoshimatsu.springtest.usecase.entity.HelloWorldEntity;

public interface HelloWorldUseCase {

    String printHello();

    String printMessage(String message);

    void saveHelloWorld(HelloWorldEntity helloWorldEntity);

}
