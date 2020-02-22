package com.amongalen.buildsvault;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class BuildsVaultApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuildsVaultApplication.class, args);
    }
}
