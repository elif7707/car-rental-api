package com.arackiralama.sistem;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // states that this class is a controller which meets the HTTP requests
@RequestMapping("/api/musteriler")

public class MusteriController {

    private final MusteriService musteriService; // encapsulation

    public MusteriController(MusteriService musteriService) {
        this.musteriService = musteriService;
    }

    // HTTP GET isteği geldiğinde tüm müşterileri listeleyen yapı
    @GetMapping
    public List<Musteri> tumMusterileriGetir() {
        return musteriService.tumMusterileriGetir();
    }

    @GetMapping("/{id}")
    public Musteri musteriGetir(@PathVariable Long id) {
        return musteriService.musteriGetir(id);
    }

    @PostMapping
    public Musteri musteriEkle(@RequestBody Musteri musteri) {
        return musteriService.musteriEkle(musteri);
    }


    @PutMapping("/{id}")
    public Musteri musteriGuncelle(@PathVariable Long id, @RequestBody Musteri yeniBilgiler) {
        return musteriService.musteriGuncelle(id, yeniBilgiler);
    }


    @DeleteMapping("/{id}")
    public void musteriSil(@PathVariable Long id) {
        musteriService.musteriSil(id);
    }



}
