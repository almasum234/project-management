package me.project.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Data
@ApiModel("UpdateProjectDto")
public class UpdateProjectDto {

    @NotNull(message = "Project status can't be empty")
    @ApiModelProperty("Project status integer [0 for pre] [1 for start] [3 for end]")
    private Integer status;

    @ApiModelProperty("Project StartDateTime, format: yyyy-MM-dd HH:mm:ss")
    private String startDateTime;

    @ApiModelProperty("Project endDateTime, format: yyyy-MM-dd HH:mm:ss")
    private String endDateTime;
}
