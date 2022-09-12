package me.project.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author masum
 */
@Data
@ApiModel("save project")
public class AddProjectDTO {

    @NotBlank(message = "Project name is mandatory")
    @Size(max = 20, message = "name")
    private String name;

    @NotBlank(message = "introduce is mandatory")
    @ApiModelProperty("intro")
    private String intro;

    @NotBlank(message = "Owner name is mandatory")
    private String owner;

//    @NotNull(message = "Status can not empty")
//    private Integer status;
//
//    private String startDateTime;
//
//    private String endDateTime;
}
