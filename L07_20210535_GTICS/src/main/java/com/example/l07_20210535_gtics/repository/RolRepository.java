package com.example.l07_20210535_gtics.repository;

import com.example.l07_20210535_gtics.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Roles,Integer> {
}
