package com.example.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)-> csrf.ignoringRequestMatchers("/saveMsg"))
                .authorizeHttpRequests((requests) ->
                requests.requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                        .requestMatchers("/displayMessages").hasRole("ADMIN")
                        .requestMatchers("/","/home").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/assets/**").permitAll())
                        .formLogin((loginConfigurer) -> loginConfigurer.loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
//  no longer of use as we now doing it with the logout controller    .logout((logoutConfigurer) -> logoutConfigurer.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("ADMIN","USER")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

}
