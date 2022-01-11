package com.example.application.data.repository;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

//    @Query("SELECT r FROM Rent r WHERE r.km IS NULL AND r.returnDate IS NULL")
//    List<Rent> findRunningRentals();

    List<Rent> findByCar(Auto auto);

    @Query("select r from Rent r " +
            "where  lower(r.car) like lower(concat('%', :searchTerm, '%') ) " +
            "or lower(r.driver) like lower(concat('%', :searchTerm, '%') ) ")
    List<Rent> search(@Param("searchTerm") String searchTerm);

}