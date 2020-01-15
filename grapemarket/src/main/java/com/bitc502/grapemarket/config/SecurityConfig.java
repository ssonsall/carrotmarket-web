package com.bitc502.grapemarket.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.bitc502.grapemarket.handler.MyLoginSuccessHandler;
import com.bitc502.grapemarket.security.oauth2.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpFirewall looseHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST"));
		firewall.setAllowSemicolon(true);
		firewall.setAllowUrlEncodedSlash(true);
		firewall.setAllowBackSlash(true);
		firewall.setAllowUrlEncodedPercent(true);
		firewall.setAllowUrlEncodedPeriod(true);
		return firewall;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests()
				// 해당주소를 타는 것은 전부 시큐리티가 막는다.
				// ex) board/접근을 세분화해서 막고싶으면 먼저 추가해주고 밑에 다막으면된다 순서 잘지켜라!!

				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/board/list").permitAll()
				.antMatchers("/board/**", "/chat/**")
				.authenticated()
				// 위의 주소를 타는 것을 제외한 접근은 시큐리티가 막지 않는다.
				.anyRequest().permitAll().and().formLogin()
				// 내 모델의 아이디와 패스워드 변수명이 username과 password가 아니라면 꼭 해줘라
				// 우리는 지금 필요없지만 나중에 다르게 쓰게된다면 보게될 설정 코드다
				.usernameParameter("username")
				.passwordParameter("password")
				// 시큐리티가 기본 제공하는 로그인페이지가 아니라 내가 커스텀한 페이지 사용한다.
				.loginPage("/user/login")
				// 이 주소를 타고 로그인이 된다 (로그인폼에서 아래의 주소로 액션을 타게 해야한다.)
				.loginProcessingUrl("/user/loginProc")
				// 로그인이 성공 할 때 핸들러 탐. 성공시 안드("/android/loginSuccess"),웹("/") 구분해서 리다이렉트
				.successHandler(successHandler())				
				.failureUrl("/user/login")
				.and()
				.logout()
				.logoutUrl("/user/logout")
				// 로그아웃에 성공했을 때 이 것은 핸들러를 사용한 것이고 로그인처럼 url을 사용해도된다.
				.logoutSuccessUrl("/")
				// .logoutSuccessHandler(new MyLogoutSuccessHandler());
				.and()
				.oauth2Login()
				.userInfoEndpoint()
				.userService(customOAuth2UserService)
				.and()
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						response.sendRedirect("/");
					}
				}).failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
						PrintWriter out = response.getWriter();
						out.println("LOGIN Fail");
						out.flush();

					}
				});

	}
@Bean
	public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
	    return new ServletListenerRegistrationBean<HttpSessionListener>(new VisitorCounter(vRepo));
	}
    @Bean
    public AuthenticationSuccessHandler successHandler() {
      return new MyLoginSuccessHandler();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties clientProperties) {

		List<ClientRegistration> registrations = clientProperties.getRegistration().keySet().stream()
				.map(provider -> getRegistration(clientProperties, provider)).filter(Objects::nonNull)
				.collect(Collectors.toList());

		return new InMemoryClientRegistrationRepository(registrations);
	}

	private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String provider) {
		if ("google".equals(provider)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
			return CommonOAuth2Provider.GOOGLE.getBuilder(provider).clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret()).scope("email", "profile").build();
		}
		return null;
	}

}