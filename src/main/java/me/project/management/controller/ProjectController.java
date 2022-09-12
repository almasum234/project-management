package me.project.management.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.project.management.dto.AddProjectDTO;
import me.project.management.dto.ProjectInfoDTO;
import me.project.management.dto.UpdateProjectDTO;
import me.project.management.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/project")
@Api(tags = "project api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @ApiOperation(value = "create project", response = ProjectInfoDTO.class)
    public ResponseEntity<ProjectInfoDTO> createProject(@RequestBody @Validated AddProjectDTO data) {
        return ResponseEntity.ok(projectService.createProject(data));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "getProjectInfo", response = ProjectInfoDTO.class)
    public ResponseEntity getProjectInfo(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProjectInfo(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "updateProjectStatus", response = ProjectInfoDTO.class)
    public ResponseEntity updateProjectStatus(@PathVariable Integer id,
                                              @RequestBody @Validated UpdateProjectDTO data) {
        validateProjectStatus(data);
        return ResponseEntity.ok(projectService.updateProjectStatus(id, data));
    }

    @GetMapping("info")
    @ApiOperation(value = "get pageProjectInfo", response = ProjectInfoDTO.class, responseContainer = "List")
    public ResponseEntity pageUserInfo(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(projectService.pageProjectInfo(pageIndex, pageSize));
    }

    private void validateProjectStatus(UpdateProjectDTO data) {
        Integer status = data.getStatus();
        if (status.equals(1) && data.getStartDateTime() == null) {
            throw new RuntimeException("Invalid project start date");
        } else if (status.equals(2) && data.getEndDateTime() == null) {
            throw new RuntimeException("Invalid project end date");
        }
    }

}
