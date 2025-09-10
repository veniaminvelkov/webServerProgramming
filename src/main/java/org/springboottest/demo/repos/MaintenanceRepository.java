package org.springboottest.demo.repos;

import org.springboottest.demo.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {

    long countByGarage_IdAndScheduledDate(Long garageId, String scheduledDate);
}
