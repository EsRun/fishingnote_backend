package com.fishing.www.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fishing.www.security.config.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity // SecurityConfig Class가 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	    
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
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
			.antMatchers("/member/**", "/login/**").permitAll()
			.antMatchers("/user/**").access("hasRole('ROLE_USER')")
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login/loginForm")
				.loginProcessingUrl("/login/loginProc")
				.defaultSuccessUrl("/")
				.and()
			.logout()
				.logoutSuccessUrl("/login/loginForm")
			.and()
			.exceptionHandling().accessDeniedPage("/error404");
	}
	
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
}
