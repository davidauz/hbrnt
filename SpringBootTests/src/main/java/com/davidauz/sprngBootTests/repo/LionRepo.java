package com.davidauz.sprngBootTests.repo;

import com.davidauz.sprngBootTests.entities.Lion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LionRepo extends JpaRepository<Lion, Long> {
}


