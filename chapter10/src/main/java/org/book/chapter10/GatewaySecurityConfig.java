package org.book.chapter10;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
 Will let Spring Security know to use this class for configuration.
 */
@EnableWebSecurity
public class GatewaySecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     We'll use our manager object to create a user and return that managers as part of
      UserDetailService
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails adminUser = User
                .withUsername("admin")
                .password(encoder().encode("admin123"))
                .authorities("FULL_PRIVILEGES")
                .roles("ADMIN")
                .build();

        manager.createUser(adminUser);
        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/dashboard").authenticated()
                .and().formLogin();
    }
}
