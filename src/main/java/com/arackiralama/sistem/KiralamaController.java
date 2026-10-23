package com.arackiralama.sistem;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiralamalar")

public class KiralamaController {

    private final KiralamaService kiralamaService;

    public KiralamaController(KiralamaService kiralamaService){
        this.kiralamaService = kiralamaService;
    }

    @GetMapping
    public List<Kiralama> tumKiralamalariGetir(){
        return kiralamaService.tumKiralamalariGetir();
    }

    @PostMapping
    public Kiralama kiralamaYap(@RequestBody KiralamaIstegi istek){
        return kiralamaService.kiralamaYap(istek);
    }

}
