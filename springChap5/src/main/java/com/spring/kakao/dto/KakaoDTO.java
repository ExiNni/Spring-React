package com.spring.kakao.dto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KakaoDTO {
    private Long id;
    private String email;
    private String nickname;
    private String name;
    private String gender;

   
}
