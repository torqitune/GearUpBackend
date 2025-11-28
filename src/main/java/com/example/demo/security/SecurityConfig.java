package com.example.demo.security;

// in a security config class we define the security configuration for our application , which APIs are public , which are protected and which are private etc

// whey do we need this ? coz by defualt spring security blocks all the APIs , so we need to define which APIs are public and which are protected

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration // Configuration class is used to define the configuration for the application
@EnableWebSecurity // EnableWebSecurity is used to enable the web security
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter; // custom filter to authenticate the user

    // constructor to inject the JWt filter
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable the CSRF protection , as we are not using cookies or sessions ,
                                              // web server generate unique tokens for each user session whne user
                                              // submits request the server validate the token with one it originally
                                              // generated.
                .cors(cors -> cors.configurationSource(request -> {  // this enables CORS , allowing frontend on another domain to call backend
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(java.util.List.of("https://gearup-69678.bubbleapps.io")); // Only allow this domain to access the backend
                    corsConfig.setAllowedMethods(java.util.List.of("GET", "POST"));     // tells which methods are allowed
                    corsConfig.setAllowedHeaders(java.util.List.of("*"));       // this allows frontend to include any header (including auth + jwt)
                    corsConfig.setAllowCredentials(false);          // no cookies allowed
                    return corsConfig;
                })) 
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // make this endpoint public
                        .requestMatchers(HttpMethod.GET, "/api/blogs/**").permitAll() // make this endpoint public
                        .requestMatchers(HttpMethod.GET, "/api/events/**").permitAll() // make this endpoint public
                        .requestMatchers(HttpMethod.GET, "/api/images/**").permitAll() // make this endpoint public
                        .anyRequest().authenticated() // while rest other endpoints are require authentication
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions , JWT only
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // add the JWT
                                                                                                       // filter before
                                                                                                       // the
                                                                                                       // UsernamePassword
                                                                                                       // , i.e before
                                                                                                       // spring checks
                                                                                                       // username/pswd
                                                                                                       // our JWT filter
                                                                                                       // will check
                                                                                                       // authorization
                                                                                                       // header for the
                                                                                                       // the token

        return http.build();
    }

    @Bean // marking it as bean so that it can be injected into other classes
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // this returns a password encoder , i.e here we are using
                                            // BCryptPasswordEncoder to salt the password before storing it in the
                                            // database
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // this method returns an authentication manager instance that is used
                                                  // to authenticate the user
    }
}
