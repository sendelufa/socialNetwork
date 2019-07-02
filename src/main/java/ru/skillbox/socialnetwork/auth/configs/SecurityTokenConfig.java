package ru.skillbox.socialnetwork.auth.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.skillbox.socialnetwork.auth.*;
import ru.skillbox.socialnetwork.auth.filters.JwtTokenAuthenticationFilter;
import ru.skillbox.socialnetwork.auth.filters.JwtUsernameAndPasswordAuthenticationFilter;
import ru.skillbox.socialnetwork.auth.handlers.CustomAuthenticationFailureHandler;
import ru.skillbox.socialnetwork.auth.handlers.CustomAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity  // Enable security config. This annotation denotes config for spring security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {   // ++
  @Autowired
  private JwtConfig jwtConfig;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        // make sure we use stateless session; session won't be used to store user's state.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // handle an authorized attempts
        .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        // Add a filter to validate the tokens with every request
        .addFilterBefore(new JwtTokenAuthenticationFilter(jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
        .addFilterAfter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig), JwtTokenAuthenticationFilter.class)
        // authorization requests config
        .authorizeRequests()
        // allow all who are accessing "auth" service
        .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
        // must be an admin if trying to access admin area (authentication is also required here)
        .antMatchers("/gallery").permitAll()
        // Any other request must be authenticated
        .anyRequest().authenticated()
        .and()
          .formLogin()
            .successHandler(new CustomAuthenticationSuccessHandler())
            .failureHandler(new CustomAuthenticationFailureHandler())
          .loginPage("/api/**").permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
