package org.weframe.kotlinresourcesserver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

  @RequestMapping("/me")
  public ResponseEntity getMe(final Principal principal) {
    DecodedJWT decodedJWT = JWT.decode(((AuthenticationJsonWebToken) principal).getToken());
    String email = decodedJWT.getClaim("https://email").asString();
    String role = decodedJWT.getClaim("scope").asString();
    return ResponseEntity.ok(email + "|" + role);

  }

}
