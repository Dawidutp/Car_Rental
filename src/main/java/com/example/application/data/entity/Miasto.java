package com.example.application.data.entity;

import lombok.*;
import org.hibernate.annotations.Formula;

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

    @Formula("(select count(c.id) from Rent c where c.id = id_miasta)")
    private int rentCount;

    public int getRentCount(){
        return rentCount;
    }

}
