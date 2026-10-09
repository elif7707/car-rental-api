package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MusteriService {

    private final MusteriRepository musteriRepository;

    public List<Musteri> musteriBilgisiGetir() {
        return musteriRepository.findAll();
    }

    public Musteri yeniMusteriEkle(Musteri musteri) {
        return musteriRepository.save(musteri);
    }

}
