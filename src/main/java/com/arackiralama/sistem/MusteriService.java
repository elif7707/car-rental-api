package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MusteriService {

    private final MusteriRepository musteriRepository;

    public List<Musteri> tumMusterileriGetir() {
        return musteriRepository.findAll();
    }

    public Musteri musteriEkle(Musteri musteri) {
        return musteriRepository.save(musteri);
    }

    public Musteri musteriGetir(Long id) {
        return musteriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    public void musteriGuncelle(Long id, Musteri yeniMusteriBilgileri) {

        Musteri mevcut = musteriGetir(id);

        mevcut.setAd(yeniMusteriBilgileri.getAd());
        mevcut.setId(yeniMusteriBilgileri.getId());
        mevcut.setYas(yeniMusteriBilgileri.getYas());
        mevcut.setSoyad(yeniMusteriBilgileri.getSoyad());
        mevcut.setEhliyetYili(yeniMusteriBilgileri.getEhliyetYili());

    }

    public void musteriSil(Long id) {

        Musteri silinecekMusteri = musteriGetir(id);
        musteriRepository.delete(silinecekMusteri);
    }

}
