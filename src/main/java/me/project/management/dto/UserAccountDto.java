package me.project.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {
    @NotBlank(message = "username is mandatory")
    @Size(max = 255, message = "User name text length should not be more then 255 character(s)")
    private String username;

    @NotBlank(message = "password is mandatory")
    @Size(max = 100, message = "Password text length should not be more then 100 character(s)")
    private String password;

    @NotBlank(message = "nickname is mandatory")
    @Size(max = 255, message = "Nick name text length should not be more then 255 character(s)")
    private String nickname;
}
