package com.movexa.api.repository;

import com.movexa.api.model.RoboAGV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoboAGVRepository extends JpaRepository<RoboAGV, Long> {
}