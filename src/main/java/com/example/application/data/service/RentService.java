package com.example.application.data.service;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Rent;
import com.example.application.data.repository.RentRepository;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    public void deleteRent(Rent rent){
        rentRepository.delete(rent);

    }
    public void saveRent(Rent rent){
        if(rent == null){
            System.err.println("Rent is null.");
            return;
        }

        rentRepository.save(rent);
    }
    public long countRents() {
        return rentRepository.count();
    }

    public List<Rent> findAllRents(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return rentRepository.findAll();
        } else {
            return rentRepository.search(filterText);
        }
    }

}
