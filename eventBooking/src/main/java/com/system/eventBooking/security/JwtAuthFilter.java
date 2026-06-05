package com.system.eventBooking.security;

import com.system.eventBooking.services.CustomUserDetailService;
import com.system.eventBooking.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;

    public JwtAuthFilter(JwtService jwtService, CustomUserDetailService customUserDetailService){
        this.jwtService = jwtService;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException
    {
        // Read auth header
        final String authHeader = request.getHeader("Authorization");

        //if no token skip, go to next filter
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //token extraction, 7 because skip first 7 symbols at "Bearer "
        final String token = authHeader.substring(7);

        //email extract
        final String email = jwtService.extractEmail(token);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // load user from db
            UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

            //validate token
            if(jwtService.isTokenValid(token, userDetails)){
                //create auth object
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                //add request details to auth object
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //pass info that user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //continue to next filter
        filterChain.doFilter(request, response);

    }

}
