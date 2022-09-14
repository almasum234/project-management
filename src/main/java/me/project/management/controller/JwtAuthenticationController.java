package me.project.management.controller;

import io.swagger.annotations.Api;
import me.project.management.dto.AuthenticationRequestDto;
import me.project.management.dto.AuthenticationResponseDto;
import me.project.management.dto.UserAccountDto;
import me.project.management.dto.UserAccountResponseDto;
import me.project.management.service.JwtUserDetailsService;
import me.project.management.service.UserAccountService;
import me.project.management.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "JWT Authentication and Login API")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserAccountDto user) {
        return ResponseEntity.ok(userAccountService.saveUserAccount(user));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserAccountResponseDto userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDto(token, userDetails.getAccountId(), userDetails.getNickname()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }
}
