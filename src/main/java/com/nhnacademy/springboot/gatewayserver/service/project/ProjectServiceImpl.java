package com.nhnacademy.springboot.gatewayserver.service.project;

import com.nhnacademy.springboot.gatewayserver.adaptor.project.ProjectAdaptor;
import com.nhnacademy.springboot.gatewayserver.domain.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectAdaptor projectAdaptor;
    @Override
    public List<ProjectDto> getProjects() {
        return projectAdaptor.getProjects();
    }
}
