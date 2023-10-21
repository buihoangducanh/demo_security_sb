package com.anhbui.demosecurity.service.impl;

import com.anhbui.demosecurity.entity.CustomUserDetails;
import com.anhbui.demosecurity.entity.User;
import com.anhbui.demosecurity.repository.UserRepository;
import com.anhbui.demosecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với tên người dùng: " + username);
        }
        return new CustomUserDetails(user);
    }



    @Override
    public void saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public boolean isUserExists(String username) {
        return userRepository.findByUsername(username)!=null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
