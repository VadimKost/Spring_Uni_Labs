package com.kpi.polyreception.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        return new InMemoryUserDetailsManager(
                User.builder().username("admin").password(bCryptPasswordEncoder().encode(
                        "admin")).roles("ADMIN").build()
        );
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ на админ-панель
                .antMatchers("/admin").hasRole("ADMIN")
                //Доступ в мой кабинет
                .antMatchers("/account").hasRole("USER")
                //Доступ разрешен всем пользователей
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/main").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessUrl("/");
        return httpSecurity.build();
    }
}
