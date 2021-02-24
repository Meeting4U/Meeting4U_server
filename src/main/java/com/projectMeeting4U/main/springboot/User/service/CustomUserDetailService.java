package com.projectMeeting4U.main.springboot.User.service;

import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);

        if(user==null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
