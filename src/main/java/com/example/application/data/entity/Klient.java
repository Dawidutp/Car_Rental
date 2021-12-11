package com.example.application.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Klient")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Klient {

    @Id
    @NotNull
    private int IdKlienta;

    @NotNull
    @Column(name = "Imie")
    private String Imie;
    @NotNull
    @Column(name = "Nazwisko")
    private String Nazwisko;


}
