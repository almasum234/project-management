package me.project.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectInfoDto {

    private Long id;

    private String name;

    private String intro;

    private String owner;

    private String status;

    private String startDateTime;

    private String endDateTime;
}
