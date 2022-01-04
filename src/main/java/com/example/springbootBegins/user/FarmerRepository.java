package com.example.springbootBegins.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer,Long> {
     Optional<Farmer> findUserByEmail(String email);
}
