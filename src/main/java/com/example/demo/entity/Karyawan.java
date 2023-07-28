package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private String status;

    private String jk;

    private String alamat;

    private LocalDate dob;

    @OneToOne(mappedBy = "karyawan", cascade = CascadeType.ALL)
    private DetailKaryawan detailKaryawan;

    @JsonIgnore
    @OneToMany(mappedBy = "karyawan", cascade = CascadeType.ALL)
    private List<Rekening> rekeningList;

    @JsonIgnore
    @OneToMany(mappedBy = "karyawan", cascade = CascadeType.ALL)
    private List<KaryawanTraining> karyawanTrainingList;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @JsonIgnore
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
