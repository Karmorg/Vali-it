package ee.bcs.valiit.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service        //siin pöördub kasutajate andmete poole
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired  //üldiselt ei tohiks enkooderis panna service kihti, peaks olema repo, kust võtame
                // kasutaja nimed ja paroolid alla ' test ' asmele.
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername (String username){
        return User.withUsername("test")
                .password(passwordEncoder.encode("test"))
                .roles("USER").build();
    }
}
