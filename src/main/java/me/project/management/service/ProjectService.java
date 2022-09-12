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

    ProjectInfoDTO createProject(AddProjectDTO data);

    ProjectInfoDTO updateProjectStatus(Integer id, UpdateProjectDTO data);

    ProjectInfoDTO getProjectInfo(Integer id);

    Page<ProjectInfoDTO> pageProjectInfo(Integer pageNum, Integer pageSize);
}
