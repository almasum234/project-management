package me.project.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.project.management.entity.Project;
import me.project.management.mapper.ProjectMapper;
import me.project.management.service.ProjectService;
import me.project.management.utils.DateUtil;
import me.project.management.dto.*;
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
                    .status(data.getStatus())
                    .build();
            this.save(project);
        } else {
            throw new RuntimeException("Project not found");
        }

        return this.convert(project);
    }

    @Override
    public ProjectInfoDTO updateProjectStatus(Integer id, UpdateProjectDTO data) {
        Project project = Optional.ofNullable(this.getById(id)).orElseThrow(() -> new RuntimeException("Project not found"));
        if (data.getStartDateTime() != null) {
            project.setStartDateTime(DateUtil.strToDt(data.getStartDateTime()));
        }
        if (data.getEndDateTime() != null) {
            project.setEndDateTime(DateUtil.strToDt(data.getEndDateTime()));
        }
        project.setStatus(data.getStatus());
        this.updateById(project);
        return this.convert(project);
    }

    @Override
    public ProjectInfoDTO getProjectInfo(Integer id) {
        return this.convert(Optional.ofNullable(this.getById(id)).orElseGet(Project::new));
    }

    @Override
    public Page<ProjectInfoDTO> pageProjectInfo(Integer pageNum, Integer pageSize) {
        Page<Project> page = this.page(new Page<>(pageNum, pageSize));

        Page<ProjectInfoDTO> pageRet = new Page<>();
        BeanUtils.copyProperties(page, pageRet);
        pageRet.setRecords(page.getRecords().stream()
                .map(project -> ProjectInfoDTO.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .intro(project.getIntro())
                        .owner(project.getOwner())
                        .status(project.getStatus())
                        .startDateTime(project.getStartDateTime() != null ? DateUtil.dtToStr(project.getStartDateTime()): null)
                        .endDateTime(project.getEndDateTime() != null ? DateUtil.dtToStr(project.getEndDateTime()): null)
                        .build())
                .collect(Collectors.toList()));

        return pageRet;
    }

    private Optional<Project> existsByName(String name) {
        return Optional.ofNullable(this.getOne(Wrappers.<Project>lambdaQuery().eq(Project::getName, name)));
    }

    private ProjectInfoDTO convert(Project project) {
        return ProjectInfoDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .intro(project.getIntro())
                .owner(project.getOwner())
                .status(project.getStatus())
                .startDateTime(project.getStartDateTime() != null ? DateUtil.dtToStr(project.getStartDateTime()): null)
                .endDateTime(project.getEndDateTime() != null ? DateUtil.dtToStr(project.getEndDateTime()): null)
                .build();
    }
}
