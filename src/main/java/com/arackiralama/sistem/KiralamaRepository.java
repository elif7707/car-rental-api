package com.arackiralama.sistem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;
@Repository
public interface KiralamaRepository extends JpaRepository<Kiralama, Long>{

    boolean existsByAracIdAndBaslangicTarihiLessThanEqualAndBitisTarihiGreaterThanEqual(
            Long aracId, LocalDate bitisTarihi, LocalDate baslangicTarihi);

}

// existsByAracIdAndBaslangicTarihiLessThanEqualAndBitisTarihiGreaterThanEqual is a derived query

// exists by: checks is there any record that fits to the condition, returns true or false
// AracId: id of the car to be rented
// BaslangicTarihiLessThanEqual: means BaslangicTarihi + LessThanEqual
// BitisTarihiGreaterThanEqual:
