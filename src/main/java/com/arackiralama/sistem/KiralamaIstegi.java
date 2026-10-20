package com.arackiralama.sistem;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class KiralamaIstegi {
    private Long aracId;
    private Long musteriId;
    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;
}