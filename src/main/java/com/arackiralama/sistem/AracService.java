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

    public Arac aracEkle(Arac arac){
        return aracRepository.save(arac);
    }


}
