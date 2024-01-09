package com.kh.spring.controller;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csv-data")
public class ApiDbController {
	/*
	 * produces = MediaType.TEXT_PLAIN_VALUE
	 * 가지고 올 데이터 타입을 지정해주는 메서드
	 * MediaType 해준 다음 텍스트 파일을 가지고 올 것인지 다른 파일을 가지고 올 것인지 작성
	 * 텍스트/ plain 미디어 타입을 생성하겠다는 표시를 해준 것
	 * 텍스트 형식의 응답을 생성
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<InputStreamResource> csvData() {

        try {
            String csvFilePath = "C:\\Users\\user1\\Downloads\\cultureMap.csv";

            // Load the file directly using FileInputStream
            InputStreamResource resource = new InputStreamResource(new FileInputStream(csvFilePath));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=cultureMap.csv");

            return ResponseEntity.ok().headers(headers).body(resource);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}