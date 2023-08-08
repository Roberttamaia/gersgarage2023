package com.gers.gers.service;

import com.gers.gers.config.UserInfoUserDetails;
import com.gers.gers.models.userInfo;
import com.gers.gers.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private userRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<userInfo> userInfo = repository.findByUsername(username);
    return   userInfo.map(UserInfoUserDetails::new)
              .orElseThrow(()->new UsernameNotFoundException("username not found"+username));

    }
}
