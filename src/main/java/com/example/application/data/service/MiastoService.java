package com.example.application.data.service;

import com.example.application.data.entity.Miasto;
import com.example.application.data.repository.MiastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MiastoService {
    @Autowired
    private MiastoRepository miastoRepository;

    public List<Miasto> findAll() {return miastoRepository.findAll();}

}
