package me.project.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.project.management.dto.UserAccountDto;
import me.project.management.entity.UserAccount;
import me.project.management.exception.ArgumentNotValidException;
import me.project.management.mapper.UserAccountMapper;
import me.project.management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Slf4j
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Optional<UserAccount> findByUsername(String name) {
        return Optional.ofNullable(this.getOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, name)));
    }

    @Override
    public UserAccount saveUserAccount(UserAccountDto data) {
        UserAccount userAccount;
        String username = data.getUsername();
        Optional<UserAccount> opt = this.findByUsername(username);
        if (!opt.isPresent()) {
            userAccount = UserAccount.builder()
                    .username(username)
                    .password(bcryptEncoder.encode(data.getPassword()))
                    .nickname(data.getNickname())
                    .build();
            this.save(userAccount);
        } else {
            log.warn("User name already exists, username: " + username);
            throw new ArgumentNotValidException("User name already exists, username: " + username);
        }
        return userAccount;
    }

}
