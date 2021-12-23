package com.itacademy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/update").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE, "/api/users/deleteUser").fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/api/users/get-current").fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/api/users/activation/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/get-current").authenticated()

                .antMatchers(HttpMethod.GET, "/api/userInfo/**").fullyAuthenticated()
                .antMatchers(HttpMethod.POST, "/api/userInfo/**").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE, "/api/userInfo/**").fullyAuthenticated()

                .antMatchers(HttpMethod.GET, "/api/serviceCenter/**").fullyAuthenticated()
                .antMatchers(HttpMethod.POST, "/api/serviceCenter/**").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE, "/api/serviceCenter/**").fullyAuthenticated()

                .antMatchers(HttpMethod.GET, "/api/expert/**").fullyAuthenticated()
                .antMatchers(HttpMethod.POST, "/api/expert/**").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE, "/api/expert/**").fullyAuthenticated()

                .antMatchers(HttpMethod.GET, "/api/post/**").fullyAuthenticated()
                .antMatchers(HttpMethod.POST, "/api/post/**").fullyAuthenticated()
                .antMatchers(HttpMethod.DELETE, "/api/post/**").fullyAuthenticated()

//                .antMatchers(HttpMethod.GET, "/api/friends/**").fullyAuthenticated()
//                .antMatchers(HttpMethod.POST, "/api/friends/**").fullyAuthenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/friends/**").fullyAuthenticated()

                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login, password, is_active from users where login=?")
                .authoritiesByUsernameQuery
                        ("select u.login, ur.role_name as role from user_role ur inner join users u on ur.user_id = u.id where u.login = ? and u.is_active = 1");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
