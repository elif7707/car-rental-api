package com.arackiralama.sistem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

public class KiralamaCevabi {

    private Long kiralamaId;
    private String musteriAd;
    private String aracMarka;
    private String aracModel;
    private String musteriSoyad;
    private String subeAdi;
    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;
    private Double toplamUcret;
}
