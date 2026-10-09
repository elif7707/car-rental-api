package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubeService {

    private final SubeRepository subeRepository; // // We call our repository to perform database operations.

    public List<Sube> subeleriGetir(){
        return subeRepository.findAll();
    }

}
