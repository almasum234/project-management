package me.project.management.service;

import lombok.extern.slf4j.Slf4j;
import me.project.management.dto.UserAccountResponseDto;
import me.project.management.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public UserAccountResponseDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountService.findByUsername(username).orElse(null);
        if (userAccount == null) {
            log.warn("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = new User(userAccount.getUsername(), userAccount.getPassword(), new ArrayList<>());
        return new UserAccountResponseDto(user, userAccount.getNickname(), userAccount.getId());
    }
}