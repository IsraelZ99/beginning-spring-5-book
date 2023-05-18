package org.book.chapter10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class GatewaySecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("user")
                .password("user123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/songs/**")
                .hasRole("USER")
                .antMatchers("/**")
                .hasRole("ADMIN")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
        return http.build();
    }

}
