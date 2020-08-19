package karol.spring.webapp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/registration").permitAll()
                .and()
                .authorizeRequests().antMatchers("/product/new").hasAuthority("ADMIN")
                .and()
//                .authorizeRequests().antMatchers("/product/*/delete").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/product/*/edit").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/category").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/category/1/products").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/category/new").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/category/*/delete").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/category/*/edit").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/company/*/edit").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/company/new").hasRole("ADMIN")
//                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and()
                .logout().permitAll();
    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(bCryptPasswordEncoder().encode("admin"))
//                .roles("ADMIN") // ROLE_STUDENT
//                .build();
//
//
//        return new InMemoryUserDetailsManager(
//                admin
//        );
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}
