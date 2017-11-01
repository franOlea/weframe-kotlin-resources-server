package org.weframe.kotlinresourcesserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

  @RequestMapping("/me")
  public ResponseEntity getMe(final Principal principal) {
    return ResponseEntity.ok().build();
  }

}
