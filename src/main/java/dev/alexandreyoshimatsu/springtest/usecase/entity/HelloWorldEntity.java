package dev.alexandreyoshimatsu.springtest.usecase.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloWorldEntity {

    private String id;
    private String mensagem;

}
