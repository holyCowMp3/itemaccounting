package viti.kaf22.itemaccounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import viti.kaf22.itemaccounting.models.enums.UserRole;
import viti.kaf22.itemaccounting.repositories.impl.DatabaseUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableJpaRepositories
public class UserAuthorizationService implements UserDetailsService {

    @Autowired
    DatabaseUserServiceImpl userRepository;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        String ip = getClientIP();

        viti.kaf22.itemaccounting.models.User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "Не найдено пользователя с адресом: "+ email);
        }
        boolean enabled;
//        enabled = (user.getRole() == UserRole.USER_VERIFIED)
//                || (user.getRole() == UserRole.DEVELOPER)
//                || (user.getRole() == UserRole.ADMINISTRATOR);
        enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return  new org.springframework.security.core.userdetails.User
                (user.getEmail(),
                        user.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        getAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> getAuthorities (UserRole role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        System.out.println(role);
        return authorities;
    }


    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private boolean emailExist(String email) {
        viti.kaf22.itemaccounting.models.User user = userRepository.getByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
