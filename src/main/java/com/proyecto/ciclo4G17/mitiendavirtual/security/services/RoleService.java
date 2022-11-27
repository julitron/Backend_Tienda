package com.proyecto.ciclo4G17.mitiendavirtual.security.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.ciclo4G17.mitiendavirtual.security.enums.RoleList;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.Role;
import com.proyecto.ciclo4G17.mitiendavirtual.security.repositories.RoleRepository;
@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Optional<Role> getByRoleName(RoleList roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
