package com.example.application.data.service;

import com.example.application.data.entity.Miasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MiastoService {
    @Autowired
    private MiastoService miastoRepository;

    public List<Miasto> findAll() {return miastoRepository.findAll();}

    public boolean existsById(Integer id){return miastoRepository.existsById(id);}
}
