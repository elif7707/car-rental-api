package com.arackiralama.sistem; // Dosyanın projedeki adresidir. Tıpkı bir evin posta adresi
// gibi, Java'nın bu sınıfı projenin neresinde bulacağını gösterir.


//Spring Boot'un bize sunduğu hazır araçları (@RestController, @GetMapping vb.) ve Java'nın
// liste yapısını (List) bu dosyanın içinde kullanabilmemiz için içeri aktarır (çağırır).

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Bu sınıfın kapısına "REST API Kapısı" tabelası asar. Spring Boot'a "Bu sınıf bir web
// sayfası (HTML) göstermeyecek, doğrudan veri (JSON formatında ham metin) döndürecek" emrini verir.

@RequestMapping("/api/araclar") // Dış dünyadaki insanların bu sınıfa ulaşacağı ana web adresini belirler. İnternet
// tarayıcısına http://localhost:8080/api/araclar yazıldığında istek doğrudan bu sınıfa düşer.


public class AracController {

    private final AracService aracService;

    // Kurucu Metot (Constructor Injection) ile Aşçımızı (Service) garsona bağlıyoruz
    public AracController(AracService aracService) {

        this.aracService = aracService;
    }

    // HTTP GET isteği geldiğinde tüm araçları listeleyen kapı
    @GetMapping
    public List<Arac> tumAraclariGetir() {

        return aracService.tumAraclariGetir();
    }

    @GetMapping("/{id}")
    public Arac aracGetir(@PathVariable Long id) {
        return aracService.aracGetir(id);
    }

    @PostMapping
    public Arac aracEkle(@RequestBody Arac arac) {
        return aracService.aracEkle(arac);
    }

    @PutMapping("/{id}")
    public Arac aracGuncelle(@PathVariable Long id, @RequestBody Arac yeniBilgiler) {
        return aracService.aracGuncelle(id, yeniBilgiler);
    }
}