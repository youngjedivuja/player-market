package com.example.transfers.repository.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public abstract class AbstractRepository<T> {
    private RestTemplate restTemplate;

    @Autowired
    protected void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Getting optional of needed entity from external service
    protected Optional<T> getForEntity(URI uri, Class<T> clazz) {
        return Optional.ofNullable(restTemplate.getForObject(uri, clazz));
    }

    //builder for URI
    protected URI getURI(String serviceUrl, String... pathSegment) {
        return UriComponentsBuilder.fromUriString(serviceUrl)
                .pathSegment(pathSegment)
                .build()
                .toUri();
    }
}
