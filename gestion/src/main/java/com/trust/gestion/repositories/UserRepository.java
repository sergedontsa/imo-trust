package com.trust.gestion.repositories;

import com.trust.gestion.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByEmail(String email);

    List<UserEntity> findByUsername(String username);

}
