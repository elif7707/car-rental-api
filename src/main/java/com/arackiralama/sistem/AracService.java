package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AracService {

    // 'final' yaparak bu yardımcının sonradan hiç değiştirilmemesini garantiye alıyoruz.
    private final AracRepository aracRepository;

    public List<Arac> tumAraclariGetir() {
        return aracRepository.findAll();
    }

    // public: This method can be accessed from outside.
    // List<Arac>: A list of vehicles will be returned.
    // findAll(): not a function you defined yourself, but one provided out-of-the-box by Spring Data JPA (JpaRepository).

    public Arac aracGetir(Long id) {
        return aracRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    public Arac aracEkle(Arac arac) {

        return aracRepository.save(arac);
    }

    public Arac aracGuncelle(Long id, Arac yeniBilgiler) {
        Arac mevcut = aracGetir(id);

        mevcut.setMarka(yeniBilgiler.getMarka());
        mevcut.setModel(yeniBilgiler.getModel());
        mevcut.setGunlukFiyat(yeniBilgiler.getGunlukFiyat());
        mevcut.setYil(yeniBilgiler.getYil());
        mevcut.setSegment(yeniBilgiler.getSegment());
        mevcut.setMusaitMi(yeniBilgiler.getMusaitMi());

        return aracRepository.save(mevcut);
    }

    public void aracSil(Long id) {

        Arac silinecekArac = aracGetir(id);
        aracRepository.delete(silinecekArac);
    }


}
