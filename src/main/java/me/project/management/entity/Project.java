package me.project.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author masum
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Project")
@TableName(value = "project")
public class Project implements Serializable {

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("intro")
    private String intro;

    @ApiModelProperty("owner")
    private String owner;

    @ApiModelProperty("status")
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "start_date_time")
    @ApiModelProperty("start_date_time")
    private Date startDateTime;

    @ApiModelProperty("end_date_time")
    private Date endDateTime;

    @ApiModelProperty("created")
    private Date created;

    @ApiModelProperty("updated")
    private Date updated;

}
