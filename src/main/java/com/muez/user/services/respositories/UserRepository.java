package com.muez.user.services.respositories;

import com.muez.user.services.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
