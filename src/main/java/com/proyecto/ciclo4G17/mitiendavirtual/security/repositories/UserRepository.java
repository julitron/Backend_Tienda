package com.proyecto.ciclo4G17.mitiendavirtual.security.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.ciclo4G17.mitiendavirtual.security.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
