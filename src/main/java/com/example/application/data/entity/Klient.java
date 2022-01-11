package com.example.application.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Klient implements Serializable {

    @Id
    @NotNull
    private int Id;
    @NotNull
    @Column(name = "Email")
    private String Email;
    @NotNull
    @Column(name = "Password")
    private String Password;
    @NotNull
    @Column(name = "Imie")
    private String Imie;
    @NotNull
    @Column(name = "Nazwisko")
    private String Nazwisko;
    @NotNull
    @Column(name = "enabled")
    private Integer enabled;
    @NotNull
    @Column(name = "role")
    private String role;


}
