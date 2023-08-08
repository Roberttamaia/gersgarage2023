package com.gers.gers.service.user;

import com.gers.gers.models.userInfo;
import com.gers.gers.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class userServImpl implements userService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final userRepository userRepository;


    @Override
    public userInfo createUser(userInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ROLE_USER").toString());

        return userRepository.save(user);
    }

    @Override
    public userInfo findUsername(String name) {
        return userRepository.findByUsername(name).get();
    }

    @Override
    public List<userInfo> showAll() {
        return userRepository.findAll();
    }
}
