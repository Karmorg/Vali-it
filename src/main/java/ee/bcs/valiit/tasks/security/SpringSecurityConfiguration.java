package ee.bcs.valiit.tasks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void  configure (HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    .antMatchers("/*'","/home").permitAll()
                    //.anyRequest().authenticated()
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
            http.cors();
            http.csrf().disable();
        }

    @Bean       // lihtsal loob ja tagastab klassi, kui kedagi peaks selle välja kutsuma
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }

}


