package me.project.management.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */

@Getter
public class PageProjectInfoResponseDto extends Page<ProjectInfoDto> {

    private final String status;
    private final String message;

    public PageProjectInfoResponseDto(Page<ProjectInfoDto> page, String status, String message) {
        super(page.getCurrent(), page.getSize(), page.getTotal(), page.isSearchCount());
        this.status = status;
        this.message = message;
    }
}
