package com.arackiralama.sistem;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "araclar")
public class Arac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String marka;
    private String model;

    @Column(name = "gunluk_fiyat") // Eğer veritabanında alt tireliyse Java'ya böyle tanıtırız
    private Double gunlukFiyat;
    private Integer yil;
    private String segment;

    @Column(name = "musait_mi")
    private Boolean musaitMi;

}
