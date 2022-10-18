package com.ennea.valuemanage.Configurations;

import com.ennea.valuemanage.security.JWT.JwtTokenFilter;
import com.ennea.valuemanage.security.RestUrlAuthFilter;
import com.ennea.valuemanage.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    UserDetailsService userDetailsService;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public RestUrlAuthFilter restUrlAuthFilter(AuthenticationManager authenticationManager){
        RestUrlAuthFilter filter = new RestUrlAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
//        http.addFilterBefore(restUrlAuthFilter(authenticationManager()),
//                UsernamePasswordAuthenticationFilter.class);
//
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().disable();

        http.authorizeRequests(authorize ->{
            authorize
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/auth/login").permitAll();})
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/v1/retailers").hasRole("REPRESENTATIVE")
                .antMatchers(HttpMethod.POST,"/api/v1/retailers").hasRole("REPRESENTATIVE")
                .antMatchers(HttpMethod.GET,"/api/v1/distributors").hasRole("MANAGER")
                .antMatchers(HttpMethod.POST,"/api/v1/distributors").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET,"/api/v1/representatives/**").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.POST,"/api/v1/attendance").hasAnyRole("MANAGER","REPRESENTATIVE")
                .antMatchers(HttpMethod.POST,"/api/v1/reports").hasAnyRole("MANAGER","REPRESENTATIVE")
                .antMatchers(HttpMethod.GET,"/api/v1/reports/today").hasAnyRole("MANAGER","REPRESENTATIVE")
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);




//        http.addFilterBefore(restUrlAuthFilter(authenticationManager()),
//                UsernamePasswordAuthenticationFilter.class);
//
//        http
//                .authorizeRequests(authorize -> {
//                    authorize
//                            .antMatchers("/h2-console/**").permitAll()
//                            .antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll();
//
//                } )
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().and()
//                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}