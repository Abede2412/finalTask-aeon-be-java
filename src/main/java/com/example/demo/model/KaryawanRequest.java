package com.example.demo.model;

import com.example.demo.entity.DetailKaryawan;
import com.example.demo.entity.Karyawan;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KaryawanRequest {

    @NotBlank(message = "Nama karyawan tidak boleh kosong")
    private String nama;

    @NotBlank(message = "alamat karyawan tidak boleh kosong")
    private String alamat;

    @NotNull(message = "dob tidak boleh null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @NotBlank(message = "status tidak boleh kosong")
    private String status;

    @NotBlank(message = "jenis kelamin tidak boleh kosong")
    private String jk;

    @NotBlank(message = "NIK tidak boleh kosong")
    @Pattern(
            regexp = "^[0-9]{16}$",
            message = "NIK berisi 16 digit angka"
    )
    private String nik;

    @NotBlank(message = "NPWP tidak boleh kosong")
    @Pattern(
            regexp = "^[0-9]{15}$",
            message = "NPWP berisi 15 digit angka"
    )
    private String npwp;

    public Karyawan buildKaryawan(){
        Karyawan karyawan = Karyawan.builder()
                .nama(nama)
                .alamat(alamat)
                .jk(jk)
                .dob(dob)
                .status(status)
                .build();

        DetailKaryawan detailKaryawan = DetailKaryawan.builder()
                .nik(nik)
                .npwp(npwp)
                .karyawan(karyawan).build();
        karyawan.setDetailKaryawan(detailKaryawan);
        return karyawan;
    }
}
