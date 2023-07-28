package com.example.demo.repository;

import com.example.demo.entity.KaryawanTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanTrainingRepository extends JpaRepository<KaryawanTraining, Long> {

    @Query("SELECT kt from KaryawanTraining kt join kt.karyawan k join kt.training t " +
            "where lower(t.tema) like %?1% " +
            "or lower(k.nama) like %?2% ")
    Page<KaryawanTraining> findAllByFilter(String namaTraining, String namaKaryawan, Pageable pageable);
}
