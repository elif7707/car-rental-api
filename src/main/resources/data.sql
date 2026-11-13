-- Sample data for running the app with Docker.
-- INSERT IGNORE skips a row if a row with the same id already exists,
-- so restarting the app does not create duplicates.

-- 1. Branches (no dependencies)
INSERT IGNORE INTO sube (id, sube_adi, sehir, adres) VALUES
(1, 'Kadıköy Şubesi', 'İstanbul', 'Moda Cad. No:10'),
(2, 'Beşiktaş Şubesi', 'İstanbul', 'Barbaros Blv. No:25'),
(3, 'Çankaya Şubesi', 'Ankara', 'Atatürk Blv. No:100');

-- 2. Cars (no dependencies)
INSERT IGNORE INTO araclar (id, marka, model, yil, segment, gunluk_fiyat, musait_mi) VALUES
(1, 'Fiat', 'Egea', 2023, 'C', 800, TRUE),
(2, 'Renault', 'Clio', 2022, 'B', 900, TRUE),
(3, 'Toyota', 'Corolla', 2024, 'C', 1200, TRUE),
(4, 'Mercedes', 'C200', 2023, 'D', 2500, FALSE),
(5, 'Volkswagen', 'Passat', 2022, 'D', 1800, TRUE);

-- The column names are the ones used in the database (gunluk_fiyat), not the ones used in Java (gunlukFiyat). This file interacts directly
-- with MySQL; there is no Jackson or Hibernate involved.

-- 3. Customers (no dependencies)
INSERT IGNORE INTO musteriler (id, ad, soyad, yas, ehliyet_yili) VALUES
(1, 'Ayşe', 'Yılmaz', 30, 2014),
(2, 'Mehmet', 'Demir', 45, 2000),
(3, 'Zeynep', 'Kaya', 24, 2021);

-- 4. Rentals (depend on cars, customers and branches)
INSERT IGNORE INTO kiralamalar (id, arac_id, musteri_id, alis_subesi_id, baslangic_tarihi, bitis_tarihi, toplam_ucret) VALUES
(1, 1, 1, 1, '2026-12-01', '2026-12-03', 2400),
(2, 2, 2, 2, '2026-12-05', '2026-12-09', 4500);

-- Usage of IGNORE:
-- We gave constant id to each row. A standard INSERT will throw an error if the ID already exists, and the application will
-- fail to launch. INSERT IGNORE, on the other hand, says: “If this ID already exists, silently skip this row.”
-- Data is added upon the initial opening. During subsequent openings, all rows are skipped, so no duplicates are created.

-- NOTE:
-- Order is important. Rentals come last, because in order to specify `arac_id = 1`, the vehicle with ID 1 must already exist.
-- First the independent tables (branch, vehicle, customer), then the ones dependent on them (rental).

-- NOTE 2:
-- The rentals in this file do not go through the checks in the service layer; they are written directly to the database.







