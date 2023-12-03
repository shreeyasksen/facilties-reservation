package io.demo.facilitiesreservation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.demo.facilitiesreservation.entities.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query(value = "SELECT * FROM facility  WHERE facility.available_from >= ?1 AND facility.available_to <= ?2 AND hotel.ID NOT IN "
            +
            "(SELECT facility_id FROM reservation WHERE (check_in >= ?1 OR check_out <= ?2))", nativeQuery = true)
    List<Facility> findAllBetweenDates(@Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);

}
