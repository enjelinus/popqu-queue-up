package com.pqu.common.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
  @GetMapping
  public String HealthCheck() {
    return "OK";
  }
}
