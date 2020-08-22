package com.nexttech.demoToken.repository;

import com.nexttech.demoToken.model.ExpiredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpiredRepository extends JpaRepository<ExpiredToken, Long> {
}
