package me.project.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author masum
 */
@Data
@ApiModel("UpdateProjectDto")
public class UpdateProjectDto {

    @NotNull(message = "Project status can't be empty")
    @ApiModelProperty("status")
    private Integer status;

    private String startDateTime;

    private String endDateTime;
}
