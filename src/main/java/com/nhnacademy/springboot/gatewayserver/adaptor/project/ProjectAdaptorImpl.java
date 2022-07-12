package com.nhnacademy.springboot.gatewayserver.adaptor.project;

import com.nhnacademy.springboot.gatewayserver.domain.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectAdaptorImpl implements ProjectAdaptor {

    private final RestTemplate restTemplate;

    @Override
    public List<ProjectDto> getProjects() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<ProjectDto> entity = new HttpEntity<>(headers);

        ResponseEntity<List<ProjectDto>> projects =
                restTemplate.exchange("http://localhost:8082/projects/1",
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>() {
                        });

        return projects.getBody();
    }
}
