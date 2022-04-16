package com.fishing.www.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.fishing.www.security.config.auth.CustomAuthenticationProvider;
import com.fishing.www.security.config.auth.CustomLoginFailedHandler;
import com.fishing.www.security.config.auth.CustomLoginSuccessHandler;
import com.fishing.www.security.config.auth.CustomLogoutSuccessHandler;

@Configuration
@EnableWebSecurity // SecurityConfig Class가 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomLoginSuccessHandler loginSuccessHander;
	    
	@Autowired
	CustomLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	CustomLoginFailedHandler customLoginFailedHandler;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/resources/**");
    }
	
	// Custom AuthenticationProvider 등록
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/login/loginForm", "/member/joinForm", "/member/join").permitAll()
			.antMatchers("/user/**").authenticated()
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login/loginForm")
				.loginProcessingUrl("/login/login")
				.successHandler(loginSuccessHander)
				.failureHandler(customLoginFailedHandler)
				.and()
			.logout()
				.logoutUrl("/logout")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessHandler(logoutSuccessHandler)
			.and()
			.exceptionHandling().accessDeniedPage("/404");
		
		http.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false)
			.expiredUrl("/expired")
			.sessionRegistry(sessionRegistry());
	}
	
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
	
	@Bean
	public SessionRegistry sessionRegistry() {
	  return new SessionRegistryImpl();
	}// Register HttpSessionEventPublisher

	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	  return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
}
