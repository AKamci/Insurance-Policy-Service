package PolicyProject.policyService.infrastructure.persistence.repository;

import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("SELECT h FROM House h " +
            "JOIN h.building b " +
            "JOIN b.address a " +
            "WHERE h.number = :number " +
            "AND b.apartmentNumber = :apartmentNumber " +
            "AND a.city = :city " +
            "AND a.district = :district " +
            "AND a.neighborhood = :neighborhood")
    House findHouseByDetails(
            @Param("neighborhood") String neighborhood,
            @Param("district") String district,
            @Param("city") String city,
            @Param("apartmentNumber") int apartmentNumber,
            @Param("number") int number
    );

}