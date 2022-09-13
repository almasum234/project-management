package me.project.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.project.management.dto.AddProjectDTO;
import me.project.management.dto.ProjectInfoDTO;
import me.project.management.dto.UpdateProjectDTO;
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

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author masum
 */
@Slf4j
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectInfoDTO createProject(AddProjectDTO data) {
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
            throw new ArgumentNotValidException("Project name already exists, name:" + name);
        }

        return this.mapProjectInfoDTO(project);
    }

    @Override
    public ProjectInfoDTO updateProjectStatus(Integer id, UpdateProjectDTO data) {
        Project project = Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found  by id:" + id));
        if (data.getStartDateTime() != null) {
            project.setStartDateTime(DateUtil.strToDt(data.getStartDateTime()));
        }
        if (data.getEndDateTime() != null) {
            project.setEndDateTime(DateUtil.strToDt(data.getEndDateTime()));
        }
        project.setStatus(data.getStatus());
        this.updateById(project);
        return this.mapProjectInfoDTO(project);
    }

    @Override
    public ProjectInfoDTO getProjectInfo(Integer id) {
        return this.mapProjectInfoDTO(Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found by id:" + id)));
    }

    @Override
    public Page<ProjectInfoDTO> pageProjectInfo(Integer pageNum, Integer pageSize) {
        Page<Project> page = this.page(new Page<>(pageNum, pageSize));

        Page<ProjectInfoDTO> pageProjects = new Page<>();
        BeanUtils.copyProperties(page, pageProjects);
        pageProjects.setRecords(page.getRecords().stream()
                .map(this::mapProjectInfoDTO)
                .collect(Collectors.toList()));

        return pageProjects;
    }

    private Optional<Project> existsByName(String name) {
        return Optional.ofNullable(this.getOne(Wrappers.<Project>lambdaQuery().eq(Project::getName, name)));
    }

    private ProjectInfoDTO mapProjectInfoDTO(Project project) {
        Status status = Status.getByValue(project.getStatus()).orElse(null);
        return ProjectInfoDTO.builder()
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
