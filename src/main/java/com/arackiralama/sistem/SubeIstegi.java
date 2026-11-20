package com.arackiralama.sistem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubeIstegi {

    @NotBlank
    private String subeAdi;
    @NotBlank
    private String sehir;
    @NotBlank
    private String adres;
}
