package com.learning.cursodio.auth.services;

import com.learning.cursodio.auth.entities.Role;
import com.learning.cursodio.auth.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String roleName) {
        if (roleRepository.findByName(roleName).isPresent()) {
            throw new RuntimeException("Role já existe");
        }

        Role role = new Role(roleName);
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
