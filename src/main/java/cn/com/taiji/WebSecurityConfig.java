package cn.com.taiji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configuresGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("root").password("root").roles("USER")
		.and().withUser("rui").password("666").roles("ADMIN")
		.and().withUser("xiao").password("777").roles("USER");

	}

	protected void configure(HttpSecurity http) throws Exception {
		/*
		 http.authorizeRequests() .anyRequest().authenticated() .and() .formLogin()
		 .and() .httpBasic();
		 */
		 
		http.authorizeRequests()
		.antMatchers("/webjars/**", "/signup", "/about").permitAll()
		.anyRequest()
		.authenticated()
		.and().formLogin()
		.loginPage("/login").permitAll()
		.successForwardUrl("/home")
		.and()
		.logout();
		http.csrf().disable();
		
		

	}

}
