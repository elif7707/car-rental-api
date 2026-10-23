package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KiralamaService {

    private final KiralamaRepository kiralamaRepository;
    private final AracService aracService;
    private final MusteriService musteriService;

    public List<Kiralama> tumKiralamalariGetir() {
        return kiralamaRepository.findAll();
    }

    public Kiralama kiralamaYap(KiralamaIstegi istek) {
        // 1-2. Araç ve müşteri var mı? Yoksa 404
        Arac arac = aracService.aracGetir(istek.getAracId());
        Musteri musteri = musteriService.musteriGetir(istek.getMusteriId());

        // 3. Bitiş, başlangıçtan önce olamaz → 400
        if (istek.getBitisTarihi().isBefore(istek.getBaslangicTarihi())) {
            throw new InvalidRequestException("End date cannot be before start date");
        }

        // 4. Araç hizmette mi? → 409
        if (!arac.getMusaitMi()) {
            throw new ConflictException("Car is not available for rental");
        }

        // 5. Aynı araç için tarih çakışması → 409
        if (kiralamaRepository.existsByAracIdAndBaslangicTarihiLessThanEqualAndBitisTarihiGreaterThanEqual(
                arac.getId(), istek.getBitisTarihi(), istek.getBaslangicTarihi())) {
            throw new ConflictException("Car is already rented for these dates");
        }

        // 6. Ücret: gün sayısı × günlük fiyat
        long gunSayisi = ChronoUnit.DAYS.between(istek.getBaslangicTarihi(), istek.getBitisTarihi()) + 1;
        double toplamUcret = gunSayisi * arac.getGunlukFiyat();

        // 7. Kiralamayı oluştur ve kaydet
        Kiralama kiralama = new Kiralama();
        kiralama.setArac(arac);
        kiralama.setMusteri(musteri);
        kiralama.setBaslangicTarihi(istek.getBaslangicTarihi());
        kiralama.setBitisTarihi(istek.getBitisTarihi());
        kiralama.setToplamUcret(toplamUcret);
        return kiralamaRepository.save(kiralama);
    }
}