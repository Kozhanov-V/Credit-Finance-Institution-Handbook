package com.kozhanov.creditFinanceInstitutionHandbook.config;

import com.kozhanov.creditFinanceInstitutionHandbook.security.JwtAuthenticationEntryPoint;
import com.kozhanov.creditFinanceInstitutionHandbook.security.JwtRequestFilter;
import com.kozhanov.creditFinanceInstitutionHandbook.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    @Lazy
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET,
                        "/authenticate",
                        "/api/data",
                        "/api/participantTypes",
                        "/api/availableTransferServices",
                        "/api/participantStatuses",
                        "/api/accounts/{bic}",
                        "/api/findBy/**",
                        "/api/regulationAccountTypes",
                        "/api/accountStatuses",
                        "/api/accountRestrictions"

                ).permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "/api/filter",
                        "/api/token/check"

                ).permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/save",
                        "/api/import/update",
                        "/api/account/update/{accountNumber}",
                        "/api/account/save"
                ).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/api/delete/{bic}"
                ).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/api/update/{bic}",
                        "/api/account/{accountNumber}"
                ).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/api/favorites/**"
                ).hasRole("USER")
                .and().
                exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
