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
import java.util.Optional;
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

//đăng kí tài khoản
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
            signUpForm.setImage("https://firebasestorage.googleapis.com/v0/b/hoanghungmanh1.appspot.com/o/images%2Fanh-bia-chat.png?alt=media&token=e3ee6484-bba6-4045-a058-3a72cd9c3188");
        }
        Users users = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getPhone(),signUpForm.getBirthday(),signUpForm.getAvatar(),signUpForm.getImage());
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
//Đăng nhập User
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Users currentUser = userService.findByUsername(userPrinciple.getUsername()).get();
        if (userService.checkLogin(currentUser)){
            return ResponseEntity.ok(new JwtResponse(currentUser.getId(),token, userPrinciple.getName(), userPrinciple.getAvatar(),userPrinciple.getImage(), userPrinciple.getAuthorities()));
        }else
            return new ResponseEntity<>(new ResponseMessage("Error 404"), HttpStatus.NOT_FOUND);

    }
// thay đổi profile
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

 // thay đổi mk
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
//thay dổi avatar
    @PutMapping("/change-avatar")
    public ResponseEntity<?> ChangeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatarForm changeAvatarForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUserNameFromToken(jwt);
        Users users;

        return null;
    }

    // Update mat khau

    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody ChangePasswordForm changePasswordForm, @PathVariable Long id){
        Users userOptional = userService.findById(id);
        boolean matches= passwordEncoder.matches(changePasswordForm.getCurrentPassword(),userOptional.getPassword());
        boolean matches1= passwordEncoder.matches(changePasswordForm.getCurrentPassword(),changePasswordForm.getNewPassword());
        if (matches){
            if (!matches1){
                Users users = new Users(userOptional.getId(),userOptional.getName(),userOptional.getUsername(),userOptional.getEmail(),
                        passwordEncoder.encode(changePasswordForm.getNewPassword()),userOptional.getPhone(),userOptional.getBirthday(),userOptional.getAvatar(),
                        userOptional.getImage(),userOptional.getAddress(),userOptional.getInterests(),userOptional.getRoles(),userOptional.getSex());
                userService.save(users);
                return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
            }else return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
        }else return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
    }

    @PutMapping("/forgot-password/{username}")
    public ResponseEntity<?> forgotPassword(@RequestBody ChangeProfileForm changeProfileForm, @PathVariable String username){
        Optional<Users> userOptional = userService.findByUsername(username);
       if (username.equals(userOptional.get().getUsername())){
           if (changeProfileForm.getEmail().equals(userOptional.get().getEmail())){
               if (changeProfileForm.getPhone().equals(userOptional.get().getPhone())){
                   return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
               }else return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
           }else return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
       }
        return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
    }


    @PutMapping("/new-password")
    public ResponseEntity<?> updatePassword(@RequestBody SignInForm signInForm){
        Optional<Users> userOptional = userService.findByUsername(signInForm.getUsername());
        Users users = new Users(userOptional.get().getId(),userOptional.get().getName(),userOptional.get().getUsername(),userOptional.get().getEmail(),
                passwordEncoder.encode(signInForm.getPassword()),userOptional.get().getPhone(),userOptional.get().getBirthday(),userOptional.get().getAvatar(),
                userOptional.get().getImage(),userOptional.get().getAddress(),userOptional.get().getInterests(),userOptional.get().getRoles(),userOptional.get().getSex());
        userService.save(users);
        return new ResponseEntity<>(new ResponseMessage("yes"),HttpStatus.OK);
    }
}
