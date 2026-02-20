package kz.shyngys.webfluxsecurity.rest;

import kz.shyngys.webfluxsecurity.dto.AuthRequestDto;
import kz.shyngys.webfluxsecurity.dto.AuthResponseDto;
import kz.shyngys.webfluxsecurity.dto.UserDto;
import kz.shyngys.webfluxsecurity.entity.UserEntity;
import kz.shyngys.webfluxsecurity.mapper.UserMapper;
import kz.shyngys.webfluxsecurity.security.CustomPrincipal;
import kz.shyngys.webfluxsecurity.security.SecurityService;
import kz.shyngys.webfluxsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1 {
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody UserDto userDto) {
        UserEntity entity = userMapper.map(userDto);
        return userService.registerUser(entity)
                .map(userMapper::map);
    }

    @PostMapping("/login")
    public Mono<AuthResponseDto> login(@RequestBody AuthRequestDto dto) {
        return securityService.authenticate(dto.getUsername(), dto.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthResponseDto.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build()
                ));
    }

    @GetMapping("/info")
    public Mono<UserDto> getUserInfo(Authentication authentication) {
        CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();
        return userService.getUserById(customPrincipal.getId())
                .map(userMapper::map);
    }
}
