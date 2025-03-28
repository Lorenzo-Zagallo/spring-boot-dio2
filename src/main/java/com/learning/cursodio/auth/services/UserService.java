package com.learning.cursodio.auth.services;

import com.learning.cursodio.auth.entities.Role;
import com.learning.cursodio.auth.entities.User;
import com.learning.cursodio.auth.repositories.RoleRepository;
import com.learning.cursodio.auth.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        Role role1 = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("RoleService não encontrada"));

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role1);

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
