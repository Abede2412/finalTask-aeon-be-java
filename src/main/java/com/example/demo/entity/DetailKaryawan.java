package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "detail_karyawan")
@NoArgsConstructor
@AllArgsConstructor
public class DetailKaryawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nik;

    private String npwp;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawan;
}
