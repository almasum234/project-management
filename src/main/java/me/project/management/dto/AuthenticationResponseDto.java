package me.project.management.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Data
public class AuthenticationResponseDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwtToken;
    private Long accountId;
    private String nickname;

    public AuthenticationResponseDto(String jwtToken, Long accountId, String nickname) {
        this.jwtToken = jwtToken;
        this.accountId = accountId;
        this.nickname = nickname;
    }
}