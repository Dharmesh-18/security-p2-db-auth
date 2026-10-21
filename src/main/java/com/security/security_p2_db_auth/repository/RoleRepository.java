package com.security.security_p2_db_auth.repository;

import com.security.security_p2_db_auth.entity.Role;
import com.security.security_p2_db_auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {}