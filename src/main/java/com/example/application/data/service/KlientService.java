package com.example.application.data.service;

import com.example.application.data.entity.Klient;
import com.example.application.data.repository.KlientRepository;
import com.example.application.data.util.MessagesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class KlientService {

    @Autowired
    private KlientRepository klientRepository;
    @Autowired
    private MessagesBean messages;

    public List<Klient> findAll() {
        return klientRepository.findAll();
    }

    public Klient create(Klient klient) {
        if (klientRepository.existsById(klient.getIdKlienta())) {
            throw new EntityExistsException(messages.get("customerAlreadyExists"));
        }
        return klientRepository.save(klient);
    }

    public boolean existsById(Integer id) {
        return klientRepository.existsById(id);
    }
}

