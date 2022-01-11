package com.example.application.data.service;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Klient;
import com.example.application.data.entity.Miasto;
import com.example.application.data.repository.AutoRepository;
import com.example.application.data.util.MessagesBean;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private MiastoService miastoService;
    @Autowired
    private MessagesBean messages;

    public List<Auto> findAll() {
        return autoRepository.findAll();
    }

    public List<Auto> findAutoByMiasto(Miasto miasto) {
        return autoRepository.findAutoByMiasto(miasto);
    }

    public List<Auto> findAutoByPrzebiegLessThan(Integer przebieg) {
        return autoRepository.findAutoByPrzebiegLessThan(przebieg);
    }
    public List<Auto> findAllCars(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return autoRepository.findAll();
        } else {
            return autoRepository.search(filterText);
        }
    }

    public Auto create(Auto auto) {
        if (auto.getMiasto() == null) {
            throw new IllegalArgumentException(messages.get("Auto nie istnieje"));
        }
        if (auto.getMiasto().getId_miasta() == null || !miastoService.existsById(auto.getMiasto().getId_miasta())) {
            throw new EntityNotFoundException(messages.get("Miasto nie istnieje"));
        }
        if (autoRepository.existsById(auto.getVINnumber())) {
            throw new EntityExistsException(messages.get("Auto istnieje"));
        }
        return autoRepository.save(auto);

        }
    public void deleteCar(Auto auto){
        autoRepository.delete(auto);

    }
    public void saveCar(Auto auto){
        if(auto == null){
            System.err.println("Car is null.");
            return;
        }

        autoRepository.save(auto);
    }
}