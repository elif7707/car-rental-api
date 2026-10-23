package com.arackiralama.sistem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "kiralamalar")
@Getter
@Setter
@NoArgsConstructor

public class Kiralama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many is for Kiralama because its the name of the class, and One is for both arac and musteri.
    @ManyToOne
    @JoinColumn(name = "arac_id", nullable = false) // nullable = false means this column cannot be left blank
    private Arac arac;

    @ManyToOne
    @JoinColumn(name = "musteri_id", nullable = false)
    private Musteri musteri;

    @ManyToOne
    @JoinColumn(name = "alis_subesi_id", nullable = false)
    private Sube alisSubesi;

    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;
    private Double toplamUcret;

}
