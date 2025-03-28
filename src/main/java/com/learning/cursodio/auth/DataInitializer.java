package com.learning.cursodio.auth;

import com.learning.cursodio.auth.entities.Role;
import com.learning.cursodio.auth.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se a ROLE_USER já existe no banco de dados
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            // Se não existe, cria a ROLE_USER
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleRepository.save(roleUser);
            System.out.println("Role ROLE_USER criada com sucesso!");
        } else {
            System.out.println("Role ROLE_USER já existe no banco.");
        }
    }
}
