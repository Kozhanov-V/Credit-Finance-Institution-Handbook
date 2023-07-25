package com.kozhanov.creditFinanceInstitutionHandbook.security;

import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.CurrentUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (!request.getRequestURI().equals("/authenticate")) {
            final String requestTokenHeader = request.getHeader("Authorization");

            if (requestTokenHeader == null) {
                chain.doFilter(request, response);
                return;
            }
            String username = null;
            String jwtToken = null;
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                    currentUserService.setCurrentUser(userRepository.findByUsername(username).getId());
                    userDetailsService.loadUserByUsername(username);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        currentUserService.setCurrentUser(0L);
        chain.doFilter(request, response);
    }


}

