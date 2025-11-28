package com.example.demo.security;

// this class is responsible for authenticating the user before allowing them to access protected resources
//i.e it checks every request for a valid JWT token before it reaches the controller

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // marking this class as a component so that it can be injected into other
           // classes
public class JwtAuthenticationFilter extends OncePerRequestFilter { // onceperrequestfilter ensures that the filter is
                                                                    // only called once per request

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request); // this will extract the JWT token from the request

            // check if the token is not null and is valid
            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
                // extract the email from the token
                String email = jwtTokenProvider.getEmailFromToken(jwt);

                // load the user details from the database
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // create an authentication object with the user details, password (null), and
                // authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // setting some additional details like ip address and session id
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // setting authentication in the spring security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.err.println("Could not set user authentication: " + e.getMessage());
        }

        // continue the filter chain
        filterChain.doFilter(request, response);
    }

    // helper method to extract JWT token from the Authorization header
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // check if the header contains a Bearer token
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // remove "Bearer " prefix and return the token
        }

        return null;
    }

    // Skip JWT authentication for public endpoints
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // Skip filter for auth endpoints (login, register)
        return path.startsWith("/api/auth/");
    }

}
