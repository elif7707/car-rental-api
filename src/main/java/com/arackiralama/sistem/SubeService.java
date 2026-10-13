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


}
