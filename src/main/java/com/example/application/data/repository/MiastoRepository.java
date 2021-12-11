package com.example.application.data.repository;

import com.example.application.data.entity.Miasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiastoRepository extends JpaRepository<Miasto, Integer> {

}
