package com.arackiralama.sistem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sube")
@Getter
@Setter
@NoArgsConstructor

public class Sube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "sube_adi") // Added to match the sube_adi column in the DB.
    private String subeAdi;
    private String sehir;
    private String adres;
}
