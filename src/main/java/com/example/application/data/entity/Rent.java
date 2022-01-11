package com.example.application.data.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
//    @NotNull
//    @Column(name = "Km")
//    private Integer km;

    @ManyToOne
    @NotNull
    private Klient driver;

    @ManyToOne
    @NotNull
    private Auto car;

    @ManyToOne
    @NotNull
    private Miasto miasto;
}
