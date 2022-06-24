package com.example.casestudyteam2.controller;

import com.example.casestudyteam2.dto.request.*;
import com.example.casestudyteam2.dto.response.JwtResponse;
import com.example.casestudyteam2.dto.response.ResponseMessage;
import com.example.casestudyteam2.model.Role;
import com.example.casestudyteam2.model.RoleName;
import com.example.casestudyteam2.model.Users;
import com.example.casestudyteam2.security.jwt.JwtProvider;
import com.example.casestudyteam2.security.jwt.JwtTokenFilter;
import com.example.casestudyteam2.security.userpincal.UserPrinciple;
import com.example.casestudyteam2.service.impl.RoleServiceImpl;
import com.example.casestudyteam2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("no_user"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMessage("no_email"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
        }
        Users users = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getPhone(),signUpForm.getBirthday(),signUpForm.getAvatar());
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        users.setRoles(roles);
        userService.save(users);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }
    @PutMapping("/change-profile")
    public ResponseEntity<?> changeProfile(HttpServletRequest request, @Valid @RequestBody ChangeProfileForm changeProfileForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        Users users;
        try {
            if(userService.existsByUsername(changeProfileForm.getUsername())){
                return new ResponseEntity<>(new ResponseMessage("no_user"), HttpStatus.OK);
            }
            if(userService.existsByEmail(changeProfileForm.getEmail())){
                return new ResponseEntity<>(new ResponseMessage("no_email"), HttpStatus.OK);
            }
            users = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> useranme"+username));
            users.setName(changeProfileForm.getName());
            users.setUsername(changeProfileForm.getUsername());
            users.setEmail(changeProfileForm.getEmail());
            users.setPhone(changeProfileForm.getPhone());
            users.setBirthday(changeProfileForm.getBirthday());

            userService.save(users);
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()),HttpStatus.NOT_FOUND );
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePasswordForm changePasswordForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        Users users;
        try{
            users = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with -> username"+username));
            boolean matches= passwordEncoder.matches(changePasswordForm.getCurrentPassword(),users.getPassword());
            if(changePasswordForm.getNewPassword()!=null){
                users.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
                userService.save(users);
            } else{
                return new ResponseEntity<>(new ResponseMessage("No"),HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
        }catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> ChangeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatarForm changeAvatarForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        Users users;

        return null;
    }
}
