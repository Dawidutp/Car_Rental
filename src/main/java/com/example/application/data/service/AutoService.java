package com.example.application.data.service;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Klient;
import com.example.application.data.entity.Miasto;
import com.example.application.data.repository.AutoRepository;
import com.example.application.data.util.MessagesBean;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    public List<Auto> findAllCars(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return autoRepository.findAll();
        } else {
            return autoRepository.search(filterText);
        }
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

    public Image generateImage(Auto auto) {
        Integer id = auto.getVINnumber();
        StreamResource sr = new StreamResource("auto", () ->  {
            Auto attached = autoRepository.findWithPropertyPictureAttachedByVINnumber(id);
            return new ByteArrayInputStream(attached.getZdjecie());
        });
        sr.setContentType("image/png");
        Image image = new Image(sr, "picture");
        return image;

    }
}