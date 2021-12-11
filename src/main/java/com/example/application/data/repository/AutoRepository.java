package com.example.application.data.repository;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Miasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {
    List<Auto> findAutoByMiasto(Miasto miasto);

//    List<Auto> findByMileageLowerThan(Integer przebieg);
}
