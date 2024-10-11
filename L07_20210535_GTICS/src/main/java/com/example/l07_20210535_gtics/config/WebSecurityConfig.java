package com.example.l07_20210535_gtics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin().loginPage("/teatroPucpLogin").loginProcessingUrl("/submitLoginForm").successHandler(new CustomAuthenticationSuccessHandler());

        http.authorizeHttpRequests()
                .requestMatchers("/Cliente", "/Cliente/**").hasAnyAuthority("CLIENTE")
                .requestMatchers("/Gerente", "/Gerente/**").hasAnyAuthority("GERENTE")
                .requestMatchers("/Admin", "/Admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }

    @Bean
    public UserDetailsManager user(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "select email, password, activo from users where email=?";
        String sql2 = "SELECT u.email, r.name FROM users u INNER JOIN roles r ON u.id_rol = r.id_rol WHERE u.email = ? AND u.activo = 1";


        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }


}
