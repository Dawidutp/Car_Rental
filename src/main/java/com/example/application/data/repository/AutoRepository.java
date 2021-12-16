package com.example.application.data.repository;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Miasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {
    List<Auto> findAutoByMiasto(Miasto miasto);

    List<Auto> findAutoByPrzebiegLessThan(Integer przebieg);
    @Query("select a from Auto a " +
    "where  lower(a.model) like lower(concat('%', :searchTerm, '%') ) " +
    "or lower(a.VINnumber) like lower(concat('%', :searchTerm, '%') ) ")
    List<Auto> search(@Param("searchTerm") String searchTerm);
}
