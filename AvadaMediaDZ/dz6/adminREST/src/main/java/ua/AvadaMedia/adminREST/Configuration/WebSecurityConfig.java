/*
package ua.AvadaMedia.adminREST.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .configurationSource(corsConfigurationSource())
                .and()
                    .csrf()
                .disable()
//                .and()
                    .authorizeRequests()
                        .antMatchers(
                                "/admin",
                                "/admin/**",
                                "/session",
                                "/MovieREST/**",
                                "/src/**",
                                "/**"
                        ).permitAll()
                        .anyRequest().authenticated()
//                .and()
//                    .authorizeRequests()
//                        .anyRequest()
//                        .fullyAuthenticated()
                .and()
                    .formLogin()
//                        .loginPage("/login")
//                        .permitAll()
                .and()
                    .httpBasic()
                .and()
                    .logout()
                    .permitAll();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
*/
