package me.project.management.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.project.management.dto.AddProjectDto;
import me.project.management.dto.ProjectInfoDto;
import me.project.management.dto.UpdateProjectDto;
import me.project.management.enums.Status;
import me.project.management.exception.ArgumentNotValidException;
import me.project.management.service.ProjectService;
import me.project.management.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Slf4j
@RestController
@RequestMapping("/api/project")
@Api(tags = "Project API")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @ApiOperation(value = "create project", response = ProjectInfoDto.class)
    public ResponseEntity<ProjectInfoDto> createProject(@Valid @RequestBody AddProjectDto data) {
        return ResponseEntity.ok(projectService.createProject(data));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "getProjectInfo", response = ProjectInfoDto.class)
    public ResponseEntity<ProjectInfoDto> getProject(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "updateProjectStatus", response = ProjectInfoDto.class)
    public ResponseEntity<ProjectInfoDto> updateProjectStatus(@PathVariable Integer id,
                                                              @Valid @RequestBody UpdateProjectDto data) {
        validateProjectStatus(data);
        return ResponseEntity.ok(projectService.updateProjectStatus(id, data));
    }

    @GetMapping("info")
    @ApiOperation(value = "get pageProjectInfo", response = ProjectInfoDto.class, responseContainer = "List")
    public ResponseEntity<Page<ProjectInfoDto>> pageUserInfo(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(projectService.getPageProjects(pageIndex, pageSize));
    }

    private void validateProjectStatus(UpdateProjectDto projectDTO) {
        if (projectDTO.getStatus() == null) {
            throw new ArgumentNotValidException("Invalid project status");
        }
        Status status = Status.getByValue(projectDTO.getStatus())
                .orElseThrow(() -> new ArgumentNotValidException("Invalid project status: " + projectDTO.getStatus()));
        if (status == Status.START && DateUtil.strToDt(projectDTO.getStartDateTime()) == null) {
            throw new ArgumentNotValidException("Invalid project start date");
        } else if (status == Status.END && DateUtil.strToDt(projectDTO.getEndDateTime()) == null) {
            throw new ArgumentNotValidException("Invalid project end date");
        }
    }

}
