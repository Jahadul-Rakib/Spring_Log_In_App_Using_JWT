package com.rakib.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.rakib.domain.RequestData;
import com.rakib.domain.dto.UserDTO;
import com.rakib.domain.repo.UserInfoRepo;
import com.rakib.domain.repo.UserRoleRepo;
import com.rakib.service.RoleService;
import com.rakib.utilities.JWTUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.rakib.domain.UserInfo;
import com.rakib.domain.UserRole;
import com.rakib.service.UserService;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepo userInfoRepo;
    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private JWTUtilities jwtUtilities;

    public HomeController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, UserInfoRepo userInfoRepo) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRepo = userInfoRepo;
    }

    @RequestMapping(value = "adduser", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) {
        List<UserRole> roles = new ArrayList<>();
        user.getRole().forEach(value -> {
            UserRole userRole = new UserRole();
            userRole.setUserRole(value);
            try {
                UserRole role = userRoleRepo.save(userRole);
                roles.add(role);
            }catch (Exception e){
                e.getMessage();
            }

        });

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(user.getUserName());
        userInfo.setUserEmail(user.getUserEmail());
        userInfo.setUserPhone(user.getUserPhone());
        userInfo.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userInfo.setRole(roles);/*role*/
        UserInfo saveUser = userService.saveUser(userInfo);
        return ResponseEntity.ok().body(ImmutableMap.of("data", saveUser));
    }

    @RequestMapping(value = "addrole", method = RequestMethod.POST)
    public ResponseEntity<?> saveRole(@RequestBody UserRole userRole) {
        UserRole role = new UserRole();
        role.setUserRole(userRole.getUserRole());
        UserRole saveRole = roleService.saveRole(role);
        return ResponseEntity.ok().body(ImmutableMap.of("data", saveRole));
    }

    @PostMapping("login")
    public ResponseEntity<?> getLogin(@RequestBody RequestData requestData) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (requestData.getUsername(), requestData.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtilities.jwtTokenProvider();
        return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(ImmutableMap.of("data", "Bearer " + token));
    }

    @GetMapping("user")
    public ResponseEntity<?> getUser(@RequestParam String email) {
        UserInfo userInfo = userInfoRepo.getUserInfoByUserEmail(email);
        return ResponseEntity.ok().body(ImmutableMap.of("data", userInfo));
    }
}
