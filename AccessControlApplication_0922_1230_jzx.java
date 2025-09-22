// 代码生成时间: 2025-09-22 12:30:26
package com.example.accesscontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableWebSecurity
# 优化算法效率
public class AccessControlApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(AccessControlApplication.class, args);
    }

    /*
     * Configure the security settings for the application. This method is overridden
     * to customize the default security configuration.
# FIXME: 处理边界情况
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /*
             * Enable CORS and disable CSRF to simplify the example. In production,
             * you should enable CSRF protection and configure CORS properly.
             */
            .cors().and()
            .csrf().disable()
# NOTE: 重要实现细节
            /*
             * Define the authorization rules for different paths.
             */
            .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            .and()
            /*
             * Define the login form settings.
             */
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
            /*
             * Define the logout settings.
# NOTE: 重要实现细节
             */
            .logout()
                .permitAll();
    }
}
