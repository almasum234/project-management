package me.project.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.project.management.dto.UserAccountDto;
import me.project.management.entity.UserAccount;

import java.util.Optional;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
public interface UserAccountService extends IService<UserAccount> {

    Optional<UserAccount> findByUsername(String name);

    UserAccount saveUserAccount(UserAccountDto data);

}
