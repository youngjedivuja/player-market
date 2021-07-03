package com.example.transfers.repository.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public abstract class AbstractRepository<T> {
    private RestTemplate restTemplate;
    private final Class<T> entity;

    @Autowired
    protected void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Getting optional of needed entity from external service
    protected Optional<T> getOptionalForEntity(URI uri, Class<T> clazz) {
        return Optional.ofNullable(restTemplate.getForObject(uri, clazz));
    }

    //getting response entity
    protected ResponseEntity<Integer> getResponseForEntity(URI uri){
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(entity, null), Integer.class);
    }

    protected T postForEntity(URI uri, T body){
        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(body, null), this.entity).getBody();
    }

    protected T putForEntity(URI uri, T body){
        return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(body, null), this.entity).getBody();
    }

    //builder for URI
    protected URI getURI(String serviceUrl, String... pathSegment) {
        return UriComponentsBuilder.fromUriString(serviceUrl)
                .pathSegment(pathSegment)
                .build()
                .toUri();
    }
}
