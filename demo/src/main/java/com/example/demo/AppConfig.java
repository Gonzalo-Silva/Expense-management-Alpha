package com.example.demo;


import javax.annotation.security.PermitAll;

import org.hibernate.type.TrueFalseType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {
    @Bean //con esta notacion encriptamos la contraseña
    public BCryptPasswordEncoder encoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure (HttpSecurity http) throws Exception {
        http .authorizeRequests()
              .antMatchers("/css/*","/Imagenes/*","/javaScript/*","/usuario/crear-usuario","/usuario/guardar-usuario","/","/ayuda").permitAll()
              .antMatchers("/**").authenticated()
              .and()
              .formLogin()
              .loginPage("/login").defaultSuccessUrl("/Home",true).permitAll()
              .and()
                    .rememberMe()
                    .tokenValiditySeconds(36000)
                    .key("contrasena")
              .and()
              .logout().permitAll()
              .logoutUrl("/logout")
              .logoutSuccessUrl("/login")
              .deleteCookies();

    }
}
