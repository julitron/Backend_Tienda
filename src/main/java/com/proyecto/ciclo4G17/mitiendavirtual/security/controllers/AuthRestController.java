package com.proyecto.ciclo4G17.mitiendavirtual.security.controllers;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Message;
import com.proyecto.ciclo4G17.mitiendavirtual.security.dtos.JwtDto;
import com.proyecto.ciclo4G17.mitiendavirtual.security.dtos.LoginUser;
import com.proyecto.ciclo4G17.mitiendavirtual.security.dtos.NewUser;
import com.proyecto.ciclo4G17.mitiendavirtual.security.enums.RoleList;
import com.proyecto.ciclo4G17.mitiendavirtual.security.jwt.JwtProvider;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.Role;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.User;
import com.proyecto.ciclo4G17.mitiendavirtual.security.services.RoleService;
import com.proyecto.ciclo4G17.mitiendavirtual.security.services.UserService;import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    @Autowired
    public AuthRestController(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,
            UserService userService, RoleService roleService, JwtProvider jwtProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUser loginUser, BindingResult bidBindingResult){
        if(bidBindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            JwtDto jwtDto = new JwtDto(jwt);
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        }
    }
    
   @PostMapping("/register")
    public ResponseEntity<Object> resgister(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getUserName(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleList.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleList.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("Registro exitoso! Inicie sesión"), HttpStatus.CREATED);
    }
    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user= this.userService.getByUserName(userName);
        if (!user.isPresent())
            return new ResponseEntity<>(new Message("No encotrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.get(), HttpStatus.OK) ;
    }
    
    /*@GetMapping("/logout")
    public ResponseEntity<Message> logOut(HttpServletResponse httpServletResponse){
        CookieUtil.clear(httpServletResponse,cookieName);
        return new ResponseEntity<>(new Message("Sesión cerrada"), HttpStatus.OK) ;
    }   */
}