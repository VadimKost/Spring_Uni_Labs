package com.kpi.polyreception.security;

import com.kpi.polyreception.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserService userService;

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(User.withUsername("admin").password(bCryptPasswordEncoder().encode("admin"))
//                .roles("ADMIN").build());
//        userDetailsList.add(User.withUsername("user").password(bCryptPasswordEncoder().encode("user"))
//                .roles("USER").build());
//
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/register").not().fullyAuthenticated()
                //Доступ на админ-панель
                .antMatchers("/admin").hasRole("ADMIN")
                //Доступ в мой кабинет
                .antMatchers("/account").hasRole("USER")
                //Доступ разрешен всем пользователей
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessUrl("/");
        return httpSecurity.build();
    }

//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
//    }
}
