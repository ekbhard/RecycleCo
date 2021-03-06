package com.nemytow.recycleCo.RecycleCo.endpoints;

import com.nemytow.recycleCo.RecycleCo.domain.Role;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.domain.VerificationToken;
import com.nemytow.recycleCo.RecycleCo.dto.OnRegistrationCompleteEvent;
import com.nemytow.recycleCo.RecycleCo.payload.request.LoginRequest;
import com.nemytow.recycleCo.RecycleCo.payload.request.SignupRequest;
import com.nemytow.recycleCo.RecycleCo.payload.response.JwtResponse;
import com.nemytow.recycleCo.RecycleCo.payload.response.MessageResponse;
import com.nemytow.recycleCo.RecycleCo.repository.UserRepository;
import com.nemytow.recycleCo.RecycleCo.security.jwt.JwtUtils;
import com.nemytow.recycleCo.RecycleCo.security.services.UserDetailsImpl;
import com.nemytow.recycleCo.RecycleCo.service.CustomException;
import com.nemytow.recycleCo.RecycleCo.service.email.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Transactional
public class AuthController {

    @Autowired
    private TokenService service;

    @Value("${app.url.current}")
    private String url;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws CustomException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = Role.ROLE_USER;
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = Role.ROLE_ADMIN;
                    roles.add(adminRole);
                } else {
                    Role userRole = Role.ROLE_USER;
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        String appUrl = url;

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
                request.getLocale(), appUrl));
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/regitrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            return ResponseEntity.badRequest().body("Your token is null");
        }
        User user = verificationToken.getUser();
        user.setEnabled();
        userRepository.save(user);
        return ResponseEntity.ok().body("User " + user.getId() + " vas enabled!");
    }
}
