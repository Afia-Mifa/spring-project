package com.example.studentCrud.configuration;

import com.example.studentCrud.jwt.JwtTokenVerifier;
import com.example.studentCrud.jwt.JwtUsernamePasswordAuthenticationFilter;
import com.example.studentCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
    private  final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;



    public AuthConfig(UserService userService, PasswordEncoder passwordEncoder, JwtConfig jwtConfig, SecretKey secretKey) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false) ;
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

     @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig,secretKey),JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","/register","/save")
                .permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();

    }
}

//.formLogin()
//        .loginPage("/login")
//        .permitAll()
//        .defaultSuccessUrl("/course")
//        .and()
//        .logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .invalidateHttpSession(true)
//        .clearAuthentication(true)
//        .deleteCookies("JSESSIONID")
//        .logoutSuccessUrl("/login");
