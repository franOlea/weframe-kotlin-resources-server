package org.weframe.kotlinresourcesserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  @RequestMapping("/ping")
  public ResponseEntity ping() {
    return ResponseEntity.ok("pong");
  }

}
