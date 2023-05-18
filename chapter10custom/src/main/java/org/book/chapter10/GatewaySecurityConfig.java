package org.book.chapter10;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true, // for Spring @Secured annotation
        jsr250Enabled = true, // for standard Java @RoleAllowed annotation
        prePostEnabled = true // for Spring pre post annotations
)
public class GatewaySecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails adminUser = User
                .withUsername("admin")
                .password(encoder().encode("admin123"))
                .authorities("FULL_PRIVILEGES")
                .roles("ADMIN")
                .build();
        UserDetails regularUser = User
                .withUsername("user")
                .password(encoder().encode("user123"))
                .authorities("READ_ACCESS")
                .roles("USER")
                .build();
        manager.createUser(adminUser);
        manager.createUser(regularUser);

        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/login").permitAll()
                .antMatchers("/dashboard_user").hasRole("USER")
                .antMatchers("/dashboard_admin").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/default")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }



}
