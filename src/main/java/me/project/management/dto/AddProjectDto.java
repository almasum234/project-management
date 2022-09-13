package me.project.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author masum
 */
@Data
@ApiModel("save project")
public class AddProjectDto {

    @NotBlank(message = "Project name is mandatory")
    @Size(max = 255, message = "Project name text length should not be more then 200 character(s)")
    private String name;

    @NotBlank(message = "Project intro is mandatory")
    @ApiModelProperty("intro")
    private String intro;

    @NotBlank(message = "Project owner name is mandatory")
    @Size(max = 255, message = "Project owner text length should not be more then 200 character(s)")
    private String owner;

}
