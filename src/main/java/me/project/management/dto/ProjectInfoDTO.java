package me.project.management.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author masum
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfoDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @ApiModelProperty("introduce")
    private String intro;

    @NotBlank
    private String owner;

    @NotBlank
    private Integer status;

    private String startDateTime;

    private String endDateTime;
}
