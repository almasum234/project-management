package me.project.management.dto;

import org.springframework.security.core.userdetails.User;


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

    public User getUser() {
        return user;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getAccountId() {
        return accountId;
    }
}
