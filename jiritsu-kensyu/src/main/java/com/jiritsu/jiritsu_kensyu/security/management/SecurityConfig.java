package com.jiritsu.jiritsu_kensyu.security.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import com.jiritsu.jiritsu_kensyu.security.AdminAccountUserDetailsService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private AdminAccountUserDetailsService service;
    @Autowired
    private PasswordEncoder encoder;

    // @Bean
    // public void confirm(DaoAuthenticationProvider man) {
    // man.setPasswordEncoder(encoder);
    // man.setUserDetailsService(service);
    // }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(encoder);
    }

    @Bean
    public WebSecurityCustomizer webCustomizer() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        return (web) -> {
            web.httpFirewall(firewall).ignoring().requestMatchers("/public/**");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> {
            login.loginProcessingUrl("/management/auth")
                    .loginPage("/management/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/management/menu")
                    .failureUrl("/management/login")
                    .permitAll();
        });
        http.logout(logout -> {
            logout.logoutUrl("/management/logout")
                    .logoutSuccessUrl("/management/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .permitAll();
        });
        http.authorizeHttpRequests(authz -> {
            authz.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers("/management/**")
                    .permitAll(); 
                    
        });

        return http.build();

    }
}
