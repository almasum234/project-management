package me.project.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author masum
 */
@Data
@ApiModel("添加用户")
public class UpdateProjectDTO {

    @NotNull(message = "status can't empty")
    @ApiModelProperty("status")
    private Integer status;

    private String startDateTime;

    private String endDateTime;
}
