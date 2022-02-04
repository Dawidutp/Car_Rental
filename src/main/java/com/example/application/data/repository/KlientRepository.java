package com.example.application.data.repository;

import com.example.application.data.entity.Klient;
import com.example.application.data.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Integer> {

    @Query("select k from Klient k " +
            "where  lower(k.Id) like lower(concat('%', :searchTerm, '%') ) " +
            "or lower(k.LastName) like lower(concat('%', :searchTerm, '%') ) ")
    List<Klient> search(@Param("searchTerm") String searchTerm);

}