package com.project.delicioso.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // tell to spring that we want this class to be managed bean or to become spring bean
@RequiredArgsConstructor // it will create a constructor using any final field that we declare here
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // because when we make a call we need to pass the JWT authentification token within the header
        final String authHeader = request.getHeader("Authorization"); // this try to extract this header
        final String jwtToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) { // check JWT Token
            filterChain.doFilter(request, response);
            return;
        }
        // try to extract the token from the authentification header
        jwtToken = authHeader.substring(7); // 7 -> because 'B' 'e' 'a' 'r' 'e' 'r' ' ' = 7
        // try to extract the userEmail
        userEmail = jwtService.extractUserEmail(jwtToken); // extract the userEmail from JWT Token
        //if userEmail is not null + user is not authenticated yet (no perform again all the checks and setting)
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // validate and check if the token is still valid
            if (jwtService.isTokenValid(jwtToken, userDetails)){ // if user is valid we need to update the SecurityContext and send the request to our dispatcher servlet
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // this object is needed by Spring and by the security context holder to update our security context
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);// always calling our filterChain to pass the hand to the next filters to be executed
    }
}
