package me.project.management.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private String name;

    private String intro;

    private String owner;

    private String status;

    private String startDateTime;

    private String endDateTime;
}
