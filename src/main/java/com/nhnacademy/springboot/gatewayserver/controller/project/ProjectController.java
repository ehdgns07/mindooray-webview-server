 package com.nhnacademy.springboot.gatewayserver.controller.project;

 import com.nhnacademy.springboot.gatewayserver.domain.project.ProjectDto;
 import com.nhnacademy.springboot.gatewayserver.service.project.ProjectService;
 import java.util.List;
 import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 @RequiredArgsConstructor
 @RequestMapping("/projects")
 public class ProjectController {

     private final ProjectService projectService;

     @GetMapping
     public List<ProjectDto> allOfProject(){

          return projectService.getProjects();
     }
 }
