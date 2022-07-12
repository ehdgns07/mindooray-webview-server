package com.nhnacademy.springboot.gatewayserver.service.project;

import com.nhnacademy.springboot.gatewayserver.domain.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getProjects();
}
