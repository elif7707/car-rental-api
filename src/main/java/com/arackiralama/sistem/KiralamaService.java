package com.arackiralama.sistem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KiralamaService {

    // Rental process needs the data of these three classes, that's why we created fields for them, Spring will fill these fields with objects. (dependency injection)
    private final KiralamaRepository kiralamaRepository;
    private final AracService aracService;
    private final MusteriService musteriService;
    private final SubeService subeService;

    public List<KiralamaCevabi> tumKiralamalariGetir() {
        return kiralamaRepository.findAll().stream()
                .map(this::cevabaDonustur)
                .toList();
    }

    public KiralamaCevabi kiralamaYap(KiralamaIstegi istek) {
        // 1-2. Araç ve müşteri var mı? Yoksa 404
        Arac arac = aracService.aracGetir(istek.getAracId());
        Musteri musteri = musteriService.musteriGetir(istek.getMusteriId());
        Sube alisSubesi = subeService.subeGetir((istek.getAlisSubesiId()));

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

        // 7. Create rental process and save.
        // NOTE: Repositories save only entities, so we map the DTO (KiralamaIstegi) to a Kiralama entity first.
        Kiralama kiralama = new Kiralama();
        kiralama.setArac(arac);
        kiralama.setMusteri(musteri);
        kiralama.setBaslangicTarihi(istek.getBaslangicTarihi());
        kiralama.setBitisTarihi(istek.getBitisTarihi());
        kiralama.setToplamUcret(toplamUcret);
        kiralama.setAlisSubesi(alisSubesi);

        Kiralama kayitli = kiralamaRepository.save(kiralama);
        return cevabaDonustur(kayitli);
    }

    private KiralamaCevabi cevabaDonustur(Kiralama kiralama) {
        KiralamaCevabi cevap = new KiralamaCevabi();

        cevap.setKiralamaId(kiralama.getId());
        cevap.setAracMarka(kiralama.getArac().getMarka());
        cevap.setAracModel(kiralama.getArac().getModel());
        cevap.setMusteriAd(kiralama.getMusteri().getAd());
        cevap.setMusteriSoyad(kiralama.getMusteri().getSoyad());
        cevap.setSubeAdi(kiralama.getAlisSubesi().getSubeAdi());
        cevap.setBaslangicTarihi(kiralama.getBaslangicTarihi());
        cevap.setBitisTarihi(kiralama.getBitisTarihi());
        cevap.setToplamUcret(kiralama.getToplamUcret());

        return cevap;
    }
}