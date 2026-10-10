package com.arackiralama.sistem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "kiralamalar")
@Getter
@Setter
@NoArgsConstructor

public class Kiralama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
