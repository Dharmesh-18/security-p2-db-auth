package com.security.security_p2_db_auth.config;

import com.security.security_p2_db_auth.entity.Permission;
import com.security.security_p2_db_auth.entity.Role;
import com.security.security_p2_db_auth.entity.User;
import com.security.security_p2_db_auth.repository.PermissionRepository;
import com.security.security_p2_db_auth.repository.RoleRepository;
import com.security.security_p2_db_auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository,
                           PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Permissions
        Permission readPerm = permissionRepository.save(new Permission("CUSTOMER_READ"));
        Permission deletePerm = permissionRepository.save(new Permission("CUSTOMER_DELETE"));

        // Roles
        Role userRole = new Role("ROLE_USER");
        userRole.getPermissions().add(readPerm);
        roleRepository.save(userRole);

        Role adminRole = new Role("ROLE_ADMIN");
        adminRole.getPermissions().add(readPerm);
        adminRole.getPermissions().add(deletePerm);
        roleRepository.save(adminRole);

        // Users: Password 'pass123'
        userRepository.save(new User("john", passwordEncoder.encode("pass123"), Set.of(userRole)));
        userRepository.save(new User("dharmesh", passwordEncoder.encode("pass123"), Set.of(adminRole)));
    }
}