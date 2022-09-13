package me.project.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author masum
 */
@Data
@ApiModel("save project")
public class AddProjectDTO {

    @NotBlank(message = "Project name is mandatory")
    @Size(max = 20, message = "name")
    private String name;

    @NotBlank(message = "Project intro is mandatory")
    @ApiModelProperty("intro")
    private String intro;

    @NotBlank(message = "Project owner name is mandatory")
    private String owner;

}
