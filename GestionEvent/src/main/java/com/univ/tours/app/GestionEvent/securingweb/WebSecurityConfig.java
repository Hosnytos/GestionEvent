package com.univ.tours.app.GestionEvent.securingweb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        /*authenticationManager.authenticationProvider(authenticationProvider());  */ 	
   
    	
    	authenticationManager
    			.userDetailsService(userDetailsService())
    			.passwordEncoder(passwordEncoder())
    		.and()
		    	.jdbcAuthentication()
		    		.dataSource(dataSource)
		    			.usersByUsernameQuery("select email as principal, mdp as credentials, true from personne where email = ?")
		    			.authoritiesByUsernameQuery("select personnes_id_perso as principal, roles_id_role as role from users_roles where personnes_id_perso = ?")
		    			;
    	
    	/*authenticationManager
        .inMemoryAuthentication()
        .withUser("pJokic@cia.com").password(passwordEncoder().encode("mdp")).roles("USER")
        .and()
        .withUser("jCruz@gign.fr").password(passwordEncoder().encode("mdp")).roles("ADMIN");*/
    	
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http
                .anonymous().and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                	.loginProcessingUrl("/index")
                    .usernameParameter("email")
                    .passwordParameter("mdp")
                    .defaultSuccessUrl("/index", true)
                    .permitAll()
                .and()
                .logout()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/index")
                	.permitAll();
    	
    	
    /*	http.authorizeRequests()
    	.antMatchers("/index").hasAnyAuthority("ADMIN");*/
    	
    	
        
        //http.authorizeRequests().antMatchers("/index").hasRole("USER");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}