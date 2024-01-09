package com.kh.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiWeatherInsertController {

	@GetMapping(value="/weather_info/add", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	 public String DBInsert() {
		// 데이터를 시작하기 전에는 StringBuilder
		StringBuilder result = new StringBuilder();
		
		try {
			String apiUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst";
            String apiKey = "1ZurK87IM/pRNYz0hrKC0e4k6g3oFWmJU5yzR/WkueR2ySJXx3FfIPb8eGCCO9thOaTdbJ87d6GQgoqxXgavaw==";
            String numOfRows = "10";
            String pageNo = "1";
            String dataType = "xml";
            String stnId = "108";
            String tmFc = "202401090600";
            String encodedApiKey = URLEncoder.encode(apiKey, "UTF-8");
            String encodedUrl = String.format("%s?serviceKey=%s&numOfRows=%s&pageNo=%s&dataType=%s&stnId=%s&tmFc=%s",
                    apiUrl, encodedApiKey, numOfRows, pageNo, dataType, stnId, tmFc);
			
            URL url = new URL(encodedUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            // 화면에 데이터가 출력될 수 있도록 readLine을 써서 출력
            String line;
            while((line = reader.readLine()) != null) {
            	result.append(line);
            }
            
            reader.close();
            conn.disconnect();
            // DB에 저장하는 메서드 실행
            insertIntoOracleDB(result.toString());
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// json 파일로 변환해서 보여주기
		String jsonResult = convertXmlToJson(result.toString());
		return jsonResult;
		// return result.toString();
	 }
	
	private String convertXmlToJson(String xmlData) {
		JSONObject jsonObj = XML.toJSONObject(xmlData);
		return jsonObj.toString();
	}
	
	private void insertIntoOracleDB(String data) {
		String jdbcUrl = "jdbc:oracle:thin:@database-1.c5q8eoyem9vs.ap-northeast-2.rds.amazonaws.com:1521:orcl";
		String username = "admin";
		String password = "fiveguys1234";
		
		try(Connection connect = DriverManager.getConnection(jdbcUrl, username, password)){
			String sql = "INSERT INTO weather (id, data) VALUES(weather_seq.NEXTVAL, ?)";
			
			try(PreparedStatement ps = connect.prepareStatement(sql)){
				ps.setString(1, data);
				ps.executeUpdate();
			} catch(Exception e) {
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
}
