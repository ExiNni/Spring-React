package com.kh.spring.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// CORS(Cross-Origin Resource Sharing)
	// Cors는 웹 브라우저에서 실행되는 자바스크립트가 다른 도메인에 접근할 수 있도록 해주는 보안 기술
	// 주로 코딩에서나 (웹에서나) API서버에서 다른 도메인으로부터 HTTP 요청을 허용하도록 설정하는데 사용
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		// CorsConfigurationSource 객체 생성
		CorsConfiguration configuration = new CorsConfiguration();
		// 허용할 오리진(주소) 목록 설정 (여기서는 http://localhost:3000 만 허용해준 것)
		// 만약에 주소가 2가지 이상이면 (,)를찍어서 추가 가능 configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3001"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		
		// 허용할 http 메서드 목록 설정
		/*
		 * configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH"));
		 * Get - 데이터 조회 용도
		 * Post - 서버에 데이터를 제출하기 위한 용도
		 * Delete - 서버에서 데이터를 삭제하기 위한 용도
		 * Put - 서버에서 데이터를 업데이트 하기 위한 용도로 클라이언트가 수정한 내용을 데이터베이스에 업데이트 하기 위해 사용
		 * Options - 실제로 요청하기 전에 브라우저가 서버한테 해당 데이터에 대해 어떤 메서드와 헤더들을 사용할 수 있는지 확인하기 위한 용도
		 * 			 CORS에서 사전 검사를 요청하는데 많이 사용
		 * Patch - Put이랑 비슷하지만 데이터에 일부만 업데이트 하기 위한 용도
		 * 
		 * */
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		// http에서 허용할 헤더 목록을 설정
		configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origins", "Content-Type", "Accept", "Authorization"));
		// 자격 증명 (쿠키나 헤더 등)을 허용할지 여부 결정
		// 로그인 상태를 유지하거나 사용자 관련 정보를 요청과 함께 전송할 때 많이 사용
		configuration.setAllowCredentials(true);
		
		// UrlBasedCorsConfigurationSource 객체 생성 등록
		// 경로 별로 다른 CORS구성을 관리할 수 있도록 도와줌
		// 경로를 설정해서 결정한 경로에만 Cors 설정을 동일하게 적용하겠다는 의미
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		// CORS 구성이 적용되도록 설정된 source를 반환
		return source;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http
//	.cors(cors -> cors.disable())
	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
	/*
	 * csrf (cross-site request fogery) 공격을 방지하기 위한 방법 중 하나
	 * CSRF 토큰이 있으며, 이 토큰은 사용자의 세션과 관련된 고유한 값으로 
	 * 각 요청에 포함돼있는지 토큰을 통해 확인하고 유효한지를 검증
	 * 사용자가 로그인할 때 마다 서버는 특별한 1회성 CSRF 토큰을 생성하고
	 * 이 토큰을 안전하게 쿠키에 저장
	 * 웹 페이지의 폼이나 Ajax 요청에서 토큰을 포함시켜야함
	 * 보통은 input hudden 필더나 헤더에 토큰을 넣어서 전송
	 * 1회성이기 때문에 DB에 저장하지 않음
	 * 토큰이 일치하지 않으면 해당 요청은 거부되거나 무시됨 
	 */
	 
	.authorizeHttpRequests(authorizeHttpRequests ->
		authorizeHttpRequests
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
	
	
	.oauth2Login(oauth2Login ->
	oauth2Login
			.successHandler(new SimpleUrlAuthenticationSuccessHandler("/loginSuccess")));
	return http.build();
	}
	
	//추후 네이버 등록한 정보를 저장
	//InMemoryClientRegistrationRepository 등록하기 위한 공간
	//naverClientRegistration 
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(
				naverClientRegistration(),
				googleClientRegistration(),
				kakaoClientRegistration());
	}
	
	
	//인증 통합 관리하는 매니저
	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository authorizedClientRepository) {
		//클라이언트 인증 처리
		OAuth2AuthorizedClientProvider authorizedClientProvider = 
				OAuth2AuthorizedClientProviderBuilder.builder()
				.authorizationCode()
				.build();
		// 클라이언트 등록 정보와 인증된 클라이언트 저장소를 설정
		DefaultOAuth2AuthorizedClientManager authorizedClientManager = 
				new DefaultOAuth2AuthorizedClientManager(
						clientRegistrationRepository,authorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		return authorizedClientManager;
	}
	
	//네이버 클라이언트의 등록 정보를 생성하는 메서드
		//클라이언트 아이디와 시크릿, 인증 후 리다이렉트 URI 설정
		public ClientRegistration naverClientRegistration() {
			return ClientRegistration.withRegistrationId("naver")	  
//					.clientId("https://developers.naver.com/apps/#/myapps 안에 적혀있는 Client ID 가지고오기")
					.clientId("HQHp_3R0uDH7Ey5eoKgv")
					//.clientSecret("https://developers.naver.com/apps/#/myapps 안에 적혀있는 Client Secret 가지고오기")				
					.clientSecret("QF6InKq5rO")
					//네이버에서 인증하고나서 OAuth2 엔드포인트 설정
					.redirectUri("http://localhost:8080/login/oauth2/code/naver")
					.clientName("Naver")
					.authorizationUri("https://nid.naver.com/oauth2.0/authorize")
					.tokenUri("https://nid.naver.com/oauth2.0/token")
					.userInfoUri("https://openapi.naver.com/v1/nid/me")
					.userNameAttributeName("response")
					.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
					.build();
		}
	
		//구글 클라이언트의 등록 정보를 생성하는 메서드
		//클라이언트 아이디와 시크릿, 인증 후 리다이렉트 URI 설정
		public ClientRegistration googleClientRegistration() {
			return ClientRegistration.withRegistrationId("google")
					.clientId("185592051656-a70vfhfsh3dpji70eqr2civ3t8ujaiv6.apps.googleusercontent.com")
					.clientSecret("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")
					.redirectUri("http://localhost:8080/login/oauth2/code/google")
					.clientName("Google")
					.authorizationUri("https://accounts.google.com/o/oauth2/auth")
					.tokenUri("https://www.googleapis.com/oauth2/v4/token")
					.userInfoUri("https://www.googleapis.com.oauth2/v3/userinfo")
					.userNameAttributeName("sub")
					.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
					.scope("openid","profile","email")
					.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
					.build();				
		}
		
		// 카카오
		public ClientRegistration kakaoClientRegistration() {
			return ClientRegistration.withRegistrationId("kakao")
					.clientId("cfc1a70784e87ec9b6cd4654f31990d2")
					.clientSecret("Y1g5uSSGrXqsDXUcOlQRprjcW80JcDzw")
					.redirectUri("http://localhost:8080/login/oauth2/code/kakao")
					.clientName("Kakao")
					.authorizationUri("https://koauth.kakao.com/oauth/authorize")
					.tokenUri("https://koauth.kakao.com/oauth/token")
					.userInfoUri("https://kapi.kakao.com/v2/user/me")
					.userNameAttributeName("id")
					.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
					.scope("profile", "account_email") // 추후 변경할 동의항목 내역
					.build();				
		}
	
}