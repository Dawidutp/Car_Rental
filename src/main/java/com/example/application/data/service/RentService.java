package com.example.application.data.service;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Rent;
import com.example.application.data.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private AutoService autoService;



    public void create(Rent rent){
        rent.setId(null);
        rent.setCar(null);
        rent.setKm(null);
        rent.setRentDate(null);
        rent.setDriver(null);
        rent.setReturnDate(null);

        rentRepository.save(rent);
    }

//    public void finish(Rent rent, FinishRentalBean finishRentalBean) {
//        rent.setMiasto(finishRentalBean.getMiasto());
//        rent.setKm(finishRentalBean.getKm());
//        rent.getCar().setMiasto(rent.getMiasto());
//        rent.getCar().setPrzebieg(rent.getCar().getPrzebieg() + rent.getKm());
//
//        rentRepository.save(rent);
//    }

    public List<Rent> findRunningRentals() {
        return rentRepository.findRunningRentals();
    }

    public List<Rent> findByCar(Auto auto) {
        return rentRepository.findByCar(auto);
    }

    public boolean canCreate(Rent rent) {
        return autoService.findAutoByMiasto(rent.getMiasto()).contains(rent.getCar());
    }
    public List<Rent> findAllRents(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return rentRepository.findAll();
        } else {
            return rentRepository.search(filterText);
        }
    }

}
