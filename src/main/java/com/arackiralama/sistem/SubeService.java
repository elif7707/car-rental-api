package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubeService {

    private final SubeRepository subeRepository; // // We call our repository to perform database operations.

    public List<Sube> tumSubeleriGetir(){

        return subeRepository.findAll();
    }

    public Sube subeGetir(Long id){
            return subeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Branch not found with id: " + id));
    }


    public Sube subeEkle(Sube sube){
        return subeRepository.save(sube); // Return the saved branch (şube) so the caller can get its generated id.
    }

    public Sube subeGuncelle(Long id, Sube yeniSubeBilgileri){

        Sube mevcut = subeGetir(id);

        mevcut.setAdres(yeniSubeBilgileri.getAdres());
        mevcut.setSubeAdi(yeniSubeBilgileri.getSubeAdi());
        mevcut.setSehir(yeniSubeBilgileri.getSehir());

        return subeRepository.save(mevcut);
    }

    public void subeSil(Long id){
        Sube silinecekSube = subeGetir(id);
        subeRepository.delete(silinecekSube);
    }


}
