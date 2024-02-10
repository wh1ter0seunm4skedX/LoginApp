/**
 * This class configures Spring Security settings for the application.
 */
package com.b7av3.loginapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    /**
     * Configures the persistent token repository for Remember Me functionality.
     *
     * @return persistentTokenRepository implementation
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    /**
     * Configures the authentication manager to use a custom user details service and password encoder.
     *
     * @param auth AuthenticationManagerBuilder object
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Configures HTTP security settings.
     *
     * @param http HttpSecurity object
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/login", "/signup", "/js/**", "/css/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureHandler(customAuthenticationFailureHandler())
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login?logout") // Redirect to login page with a query parameter indicating successful logout
                    .deleteCookies("JSESSIONID", "remember-me") // Delete cookies on logout
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .permitAll()
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository()) // Configure your implementation
                    .tokenValiditySeconds(1800) // 1800 seconds = 30 minutes
                    .and()
                .csrf().disable();
    }

    /**
     * Bean for custom authentication failure handler.
     *
     * @return CustomAuthenticationFailureHandler object
     */
    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    /**
     * Bean for password encoder.
     *
     * @return PasswordEncoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
