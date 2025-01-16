package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //injectare dependinte; spring le vede peste tot
    //request
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authConfig -> {
                    //request matching
                    authConfig.requestMatchers(HttpMethod.GET, "/", "/login", "/register", "/error", "/login-error", "/logout", "/css/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/createUser").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/user").hasAuthority("USER"); //drepturi de acces
                    authConfig.requestMatchers(HttpMethod.GET, "/admin").hasAuthority("ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/developer").hasAuthority("DEVELOPER");
                    authConfig.requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ADMIN", "DEVELOPER");
                    authConfig.requestMatchers(HttpMethod.GET, "/authorities").hasAnyAuthority("ADMIN", "DEVELOPER");
                    authConfig.requestMatchers(HttpMethod.GET, "/roles").hasAuthority("ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/books/create").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.POST, "/books/create").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.GET, "/books/delete").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.POST, "/books/delete").hasAnyAuthority("ADMIN","USER");

                    authConfig.requestMatchers(HttpMethod.GET, "/categories/create").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.POST, "/categories/create").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.GET, "/categories/delete").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.POST, "/categories/delete").hasAnyAuthority("ADMIN","USER");

                    authConfig.requestMatchers(HttpMethod.GET, "/books/add-tag").hasAnyAuthority("ADMIN","USER");
                    authConfig.requestMatchers(HttpMethod.POST, "/books/add-tag").hasAnyAuthority("ADMIN","USER");

                    authConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                            login.loginPage("/login");
                            login.defaultSuccessUrl("/");
                            login.failureUrl("/login-error");
                        }
                )

                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    logout.logoutSuccessUrl("/");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                });

        return http.build();
    }

    @Bean
    UserDetailsService myUserDetailsService(UserRepository userRepository) {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successEvent() {
        return event -> {
            System.out.println("Success Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }

    @Bean
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> failureEvent() {
        return event -> {
            System.err.println("Bad Credentials Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }
}