package com.arackiralama.sistem;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subeler")

public class SubeController {

    private final SubeService subeService;

    public SubeController(SubeService subeService) {
        this.subeService = subeService;
    }

    @GetMapping
    public List<Sube> tumSubeleriGetir() {
        return subeService.tumSubeleriGetir();
    }

    @GetMapping("/{id}")
    public Sube subeGetir(@PathVariable Long id) {
        return subeService.subeGetir(id);
    }

    @PostMapping
    public Sube subeEkle(@RequestBody Sube sube) {
        return subeService.subeEkle(sube);
    }


    @PutMapping("/{id}")
    public Sube subeGuncelle(@PathVariable Long id, @RequestBody Sube yeniBilgiler) {
        return subeService.subeGuncelle(id, yeniBilgiler);
    }


    @DeleteMapping("/{id}")
    public void subeSil(@PathVariable Long id) {
        subeService.subeSil(id);
    }


}
