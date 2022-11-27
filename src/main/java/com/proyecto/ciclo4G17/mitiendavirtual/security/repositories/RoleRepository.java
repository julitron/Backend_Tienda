package com.proyecto.ciclo4G17.mitiendavirtual.security.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.ciclo4G17.mitiendavirtual.security.enums.RoleList;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.Role;

public interface RoleRepository extends JpaRepository <Role, Integer>{
     Optional<Role> findByRoleName(RoleList roleName);
}
