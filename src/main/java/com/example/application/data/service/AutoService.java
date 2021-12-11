package com.example.application.data.service;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Miasto;
import com.example.application.data.repository.AutoRepository;
import com.example.application.data.util.MessagesBean;
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


    public List<Auto> findAutoByMiasto(Miasto miasto) {
        return autoRepository.findAutoByMiasto(miasto);
    }

/*    public List<Auto> findByMileageLowerThan(Integer przebieg) {
        return autoRepository.findByMileageLowerThan(przebieg);
    }*/

//    public Auto create(Auto auto) {
//        if (auto.getMiasto() == null) {
//            throw new IllegalArgumentException(messages.get("Auto nie istnieje"));
//        }
//        if (auto.getMiasto().getId() == null || !miastoService.existsById(auto.getMiasto().getId())) {
//            throw new EntityNotFoundException(messages.get("Miasto nie istnieje"));
//        }
//        if (autoRepository.existsById(auto.getRegistrationNumber())) {
//            throw new EntityExistsException(messages.get("Auto istnieje"));
//        }
//        return autoRepository.save(auto);
//
//    }
}