package org.springboottest.demo.repos;

import org.springboottest.demo.entities.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage,Long> {
}
