package com.codigo.alpha.apispringbootcrud.repositoty;

import com.codigo.alpha.apispringbootcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);
}
