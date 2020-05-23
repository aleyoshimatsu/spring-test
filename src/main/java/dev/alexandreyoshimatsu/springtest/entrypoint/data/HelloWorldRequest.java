package dev.alexandreyoshimatsu.springtest.entrypoint.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloWorldRequest {

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("password")
    private String password;

}
