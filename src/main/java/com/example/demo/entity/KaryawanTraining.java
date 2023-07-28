package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "karyawan_training")
@NoArgsConstructor
@AllArgsConstructor
public class KaryawanTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tanggal_training")
    private LocalDate tanggalTraining;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawan;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

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
