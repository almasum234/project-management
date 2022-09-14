package me.project.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.project.management.dto.AddProjectDto;
import me.project.management.dto.ProjectInfoDto;
import me.project.management.dto.UpdateProjectDto;
import me.project.management.entity.Project;
import me.project.management.enums.Status;
import me.project.management.exception.ArgumentNotValidException;
import me.project.management.exception.ResourceNotFoundException;
import me.project.management.mapper.ProjectMapper;
import me.project.management.service.ProjectService;
import me.project.management.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Slf4j
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectInfoDto createProject(AddProjectDto data) {
        Project project;
        String name = data.getName();
        Optional<Project> opt = this.existsByName(name);
        if (!opt.isPresent()) {
            project = Project.builder()
                    .name(data.getName())
                    .intro(data.getIntro())
                    .owner(data.getOwner())
                    .status(Status.PRE.getValue())
                    .build();
            this.save(project);
        } else {
            log.warn("Project name already exists, name: " + name);
            throw new ArgumentNotValidException("Project name already exists, name: " + name);
        }

        return this.mapProjectInfoDTO(project);
    }

    @Override
    public ProjectInfoDto updateProjectStatus(Integer id, UpdateProjectDto data) {
        Project project = Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found  by id: " + id));
        Status status = Status.getByValue(data.getStatus())
                .orElseThrow(() -> new ArgumentNotValidException("Invalid project status: " + data.getStatus()));
        if (status == Status.PRE) {
            project.setStartDateTime(null);
            project.setEndDateTime(null);
        } else if (status == Status.START) {
            project.setStartDateTime(DateUtil.strToDt(data.getStartDateTime()));
            project.setEndDateTime(null);
        } else if (status == Status.END) {
            project.setEndDateTime(DateUtil.strToDt(data.getEndDateTime()));
        }
        project.setStatus(status.getValue());
        project.setUpdated(new Date());
        this.lambdaUpdate()
                .eq(Project::getId, project.getId())
                .set(Project::getStatus, project.getStatus())
                .set(Project::getStartDateTime, project.getStartDateTime())
                .set(Project::getEndDateTime, project.getEndDateTime())
                .set(Project::getUpdated, project.getUpdated())
                .update();
//        this.updateById(project);
        return this.mapProjectInfoDTO(project);
    }

    @Override
    public ProjectInfoDto getProject(Integer id) {
        return this.mapProjectInfoDTO(Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found by id: " + id)));
    }

    @Override
    public Page<ProjectInfoDto> getPageProjects(Integer pageNum, Integer pageSize) {
        Page<Project> page = this.page(new Page<>(pageNum, pageSize));

        Page<ProjectInfoDto> pageProjects = new Page<>();
        BeanUtils.copyProperties(page, pageProjects);
        pageProjects.setRecords(page.getRecords().stream()
                .map(this::mapProjectInfoDTO)
                .collect(Collectors.toList()));

        return pageProjects;
    }

    private Optional<Project> existsByName(String name) {
        return Optional.ofNullable(this.getOne(Wrappers.<Project>lambdaQuery().eq(Project::getName, name)));
    }

    private ProjectInfoDto mapProjectInfoDTO(Project project) {
        Status status = Status.getByValue(project.getStatus()).orElse(null);
        return ProjectInfoDto.builder()
                .id(project.getId())
                .name(project.getName())
                .intro(project.getIntro())
                .owner(project.getOwner())
                .status(status != null ? status.getText() : Status.PRE.getText())
                .startDateTime(project.getStartDateTime() != null ? DateUtil.dtToStr(project.getStartDateTime()) : null)
                .endDateTime(project.getEndDateTime() != null ? DateUtil.dtToStr(project.getEndDateTime()) : null)
                .build();
    }
}
