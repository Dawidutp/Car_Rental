package com.example.application.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Miasto implements Serializable {

    @Id
    @NotNull
    @Column(name = "id_miasta")
    private Integer id_miasta;

    @NotNull
    @Column(name = "Nazwa_Miasta")
    private String Nazwa;

}
