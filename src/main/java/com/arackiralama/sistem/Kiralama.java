package com.arackiralama.sistem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kiralamalar")
@Getter
@Setter
@NoArgsConstructor

public class Kiralama {

    @Id
    @GeneratedValue()
}
