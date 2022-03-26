package com.boots.repository;

import com.boots.entity.GUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GUserRepo extends JpaRepository<GUser, String> {
}
