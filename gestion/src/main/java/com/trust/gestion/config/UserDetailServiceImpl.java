package com.trust.gestion.config;

import com.trust.gestion.entities.UserEntity;
import com.trust.gestion.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String emailOrUserName) throws UsernameNotFoundException {
        List<UserEntity> findByEmail = userRepository.findByEmail(emailOrUserName);
        List<UserEntity> findByUserName = userRepository.findByUsername(emailOrUserName);
        if (CollectionUtils.isEmpty(findByEmail) && CollectionUtils.isEmpty(findByUserName)) {
            throw new UsernameNotFoundException("User not found");
        } else {
            UserEntity user = CollectionUtils.isEmpty(findByEmail) ? findByUserName.get(0) : findByEmail.get(0);
            List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add((GrantedAuthority) user::getRole);
            return new User(user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    user.getAccountNonExpired(),
                    user.getCredentialsNonExpired(),
                    user.getAccountNonLocked(),
                    authorities);
        }

    }
}
