package com.davidauz.hbrnt.repos;

import com.davidauz.hbrnt.entities.JoinedUnions.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<Cat, Long> {
}


