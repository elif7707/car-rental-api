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
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public Musteri musteriGuncelle(Long id, Musteri yeniMusteriBilgileri) {

        Musteri mevcut = musteriGetir(id);

        mevcut.setAd(yeniMusteriBilgileri.getAd());
        mevcut.setYas(yeniMusteriBilgileri.getYas());
        mevcut.setSoyad(yeniMusteriBilgileri.getSoyad());
        mevcut.setEhliyetYili(yeniMusteriBilgileri.getEhliyetYili());

        return musteriRepository.save(mevcut); // to write the changes on db, we should save it. Otherwise it will stay only in the memory of Java.
    }

    public void musteriSil(Long id) {

        Musteri silinecekMusteri = musteriGetir(id);
        musteriRepository.delete(silinecekMusteri);
    }

}
