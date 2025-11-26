package jwt.web.token.controller;

import jwt.web.token.Security.configuration.JwtUtils;
import jwt.web.token.configuration.UserConfig;
import jwt.web.token.dto.LoginDto;
import jwt.web.token.dto.RegisterDto;
import jwt.web.token.entity.User;
import jwt.web.token.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

   private final UserRepo userRepo;

   private final JwtUtils utils;

    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto dto){
        User user=User.builder()
                .userName(dto.getUserName())
                .role(dto.getRole())
                .password(encoder.encode(dto.getPassword()))
                .build();
            userRepo.save(user);
        return "User registered sucessfully";

    }
    @PostMapping("/login")
     public String userLogin(@RequestBody LoginDto loginDto){
      User user=  userRepo.findByUserName(loginDto.getUserName()).orElseThrow(()->{
            throw new RuntimeException("User not found");
        });
      if (! encoder.matches(user.getPassword(),loginDto.getPassword())){
          throw new RuntimeException("Password is invalid");
      }

        return utils.generateToken(user.getUserName(),user.getRole());
     }
}
