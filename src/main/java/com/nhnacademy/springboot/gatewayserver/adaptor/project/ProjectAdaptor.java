package com.nhnacademy.springboot.gatewayserver.adaptor.project;

import com.nhnacademy.springboot.gatewayserver.domain.project.ProjectDto;

import java.util.List;

public interface ProjectAdaptor {
    List<ProjectDto> getProjects();
}
