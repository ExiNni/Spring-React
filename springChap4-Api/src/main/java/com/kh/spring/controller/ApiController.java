package com.kh.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {
	
	@GetMapping("/api/data")
	public String getApiData() {
		String apiUrl = "http://apis.data.go.kr/B550928/dissForecastInfoSvc";
		String apiKey = "1ZurK87IM%2FpRNYz0hrKC0e4k6g3oFWmJU5yzR%2FWkueR2ySJXx3FfIPb8eGCCO9thOaTdbJ87d6GQgoqxXgavaw%3D%3D";
		String numOfRows="10";
		String pageNo = "1";
		String type = "json";
		String dissCd = "2";
		String znCd = "11";
		
		String url = String.format("%s?"
				+ "apiKey=%s"
				+ "&"
				+ "numOfRows=%s"
				+ "&"
				+ "pageNo=%s"
				+ "&"
				+ "type=%s"
				+ "&"
				+ "dissCd=%s"
				+ "&"
				+ "znCd=%s",
				apiUrl, apiKey, numOfRows, pageNo, type, dissCd, znCd);
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		
		return response;
	}
}
