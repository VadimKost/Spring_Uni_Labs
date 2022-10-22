package com.kpi.polyreception.security;

import com.kpi.polyreception.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");
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
