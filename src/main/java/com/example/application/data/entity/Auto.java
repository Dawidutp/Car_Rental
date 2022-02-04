package com.example.application.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auto implements Serializable {


    @Id
    @NotNull
    @Column(name = "VIN")
    private int VIN;
    @NotNull
    @Column(name = "Numer_rejestracyjny")
    private String registrationNumber;
    @NotNull
    @Column(name = "Model")
    private String model;
    @NotNull
    @Column(name = "Mileage")
    private int mileage;
    @ManyToOne
    private Miasto miasto;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] zdjecie;

    public void setregistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber.strip();}

}
