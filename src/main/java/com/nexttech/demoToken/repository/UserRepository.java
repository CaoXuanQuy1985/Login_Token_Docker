package com.nexttech.demoToken.repository;

import com.nexttech.demoToken.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsernameAndPassword(String username, String password);
    UserModel findByToken(String token);
}
