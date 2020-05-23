package dev.alexandreyoshimatsu.springtest.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;

@RefreshScope
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, Logbook logbook) {
        return restTemplateBuilder
                .requestFactory(() -> factory(logbook))
                //.setConnectTimeout(Duration.ofSeconds(properties.getConnectTimeout()))
                //.setReadTimeout(Duration.ofSeconds(properties.getReadTimeout()))
                .build();
    }

    private HttpComponentsClientHttpRequestFactory factory(Logbook logbook) {
        CloseableHttpClient client = HttpClientBuilder.create()
                .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
                //https://github.com/zalando/logbook/issues/135
                .addInterceptorLast(new LogbookHttpResponseInterceptor())
                //.setMaxConnPerRoute(properties.getMaxConnectionsPerRoute())
                //.setMaxConnTotal(properties.getMaxConnectionsTotal())
                .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }

}
