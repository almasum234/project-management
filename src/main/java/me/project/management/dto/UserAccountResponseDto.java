package me.project.management.dto;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */

@Getter
public class UserAccountResponseDto extends User {

    private final User user;
    private final String nickname;
    private final Long accountId;

    public UserAccountResponseDto(User user, String nickname, Long accountId) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.nickname = nickname;
        this.accountId = accountId;
        this.user = user;
    }
}
