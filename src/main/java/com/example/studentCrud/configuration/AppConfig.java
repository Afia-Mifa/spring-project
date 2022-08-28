//package com.example.studentCrud.configuration;
//
//import com.example.studentCrud.service.RegisterService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class AppConfig extends WebSecurityConfigurerAdapter {
//
//    private  final RegisterService registerService;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public AppConfig(RegisterService registerService, PasswordEncoder passwordEncoder) {
//        this.registerService = registerService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(registerService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        return authenticationProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/register","/save").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .defaultSuccessUrl("/student")
//                .and()
//                .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .invalidateHttpSession(true)
//                    .clearAuthentication(true)
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/login");
//    }
//}
