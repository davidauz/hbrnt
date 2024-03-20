package com.davidauz.sprngBootTests.repo;

import com.davidauz.sprngBootTests.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<Cat, Long> {
}


