package org.weframe.kotlinresourcesserver;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

  @RequestMapping("/me")
  public ResponseEntity getMe(final Principal principal) {
    AuthenticationJsonWebToken token = (AuthenticationJsonWebToken) principal;
    DecodedJWT decoded = (DecodedJWT) token.getDetails();

    Map<String, String> profile = createProfile(principal, decoded);
    return ResponseEntity.ok(profile);
  }

  private Map<String, String> createProfile(final Principal principal, final DecodedJWT decoded) {
    Claim name = decoded.getClaim("https://name");
    Claim email = decoded.getClaim("https://email");
    String id = principal.getName();

    Map<String, String> profile = new HashMap<>();
    profile.put("name", name.asString());
    profile.put("email", email.asString());
    profile.put("id", id);
    return profile;
  }

}
