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

    @FutureOrPresent(message = "{futOrPres}")
    @NotNull(message = "{notNull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "{futOrPres}")
    private LocalDate returnDate;

    private Integer km;

    @ManyToOne
    @NotNull(message = "{notNull}")
    private Klient driver;

    @ManyToOne
    @NotNull(message = "{notNull}")
    private Auto car;

    @ManyToOne
    @NotNull(message = "{notNull}")
    private Miasto miasto;
}
