package viti.kaf22.itemaccounting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import viti.kaf22.itemaccounting.services.UserAuthorizationService;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserAuthorizationService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;

    }
    @Override

    protected void configure(AuthenticationManagerBuilder auth){

        auth.authenticationProvider(authProvider());

    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity
//                .userDetailsService(userDetailsService)
//                .authorizeRequests()
//                .antMatchers("/login*").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .defaultSuccessUrl("/cabinet.html")
//                .failureUrl("/login.html?error=true")
//                .and()
//                .logout().logoutSuccessUrl("/login.html");                                                                                                                                    //TODO : REPLACE AJAX
        httpSecurity.authorizeRequests()
                .antMatchers("/login*","/registration*", "/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html").permitAll()
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login-error.html")
                .and()
                .logout()
                .logoutUrl("/logout*").permitAll()
                .logoutSuccessUrl("/index")
                .and().csrf().disable();


    }

}
