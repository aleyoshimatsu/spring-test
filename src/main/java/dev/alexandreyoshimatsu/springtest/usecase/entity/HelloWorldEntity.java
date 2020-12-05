package dev.alexandreyoshimatsu.springtest.usecase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HelloWorldEntity {

    private String id;
    private String mensagem;
    private String senha;

}
