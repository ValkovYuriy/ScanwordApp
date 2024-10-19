package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.model.User;
import com.quad.ScanwordApp.model.enums.Role;
import com.quad.ScanwordApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public boolean login(String username, String password) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        System.out.println(user);
        return user != null && user.getPassword().equals(passwordEncoder.encode(password));
    }

    public boolean register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.ROLE_USER);
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        else
        {
            userRepository.save(user);
            return true;
        }
    }
}
