package me.project.management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.project.management.dto.AddProjectDto;
import me.project.management.dto.ProjectInfoDto;
import me.project.management.dto.UpdateProjectDto;
import me.project.management.entity.Project;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
public interface ProjectService extends IService<Project> {

    ProjectInfoDto createProject(AddProjectDto data);

    ProjectInfoDto updateProjectStatus(Integer id, UpdateProjectDto data);

    ProjectInfoDto getProject(Integer id);

    Page<ProjectInfoDto> getPageProjects(Integer pageNum, Integer pageSize);
}
