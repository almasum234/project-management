package me.project.management.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.project.management.dto.*;
import me.project.management.entity.Project;

/**
 * User service
 *
 * @author masum
 */
public interface ProjectService extends IService<Project> {

    ProjectInfoDto createProject(AddProjectDto data);

    ProjectInfoDto updateProjectStatus(Integer id, UpdateProjectDto data);

    ProjectInfoDto getProject(Integer id);

    Page<ProjectInfoDto> getPageProjects(Integer pageNum, Integer pageSize);
}
