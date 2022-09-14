package me.project.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("users")
@TableName(value = "user_account")
public class UserAccount {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private long id;

    @TableField(value = "username")
    @ApiModelProperty("username")
    private String username;

    @TableField(value = "nickname")
    @ApiModelProperty("nickname")
    private String nickname;

    @JsonIgnore
    @TableField(value = "password")
    @ApiModelProperty("password")
    private String password;

}

