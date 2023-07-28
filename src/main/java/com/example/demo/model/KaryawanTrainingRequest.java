package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KaryawanTrainingRequest {

    @NotNull(message = "karyawan id tidak boleh null")
    private Long karyawanId;

    @NotNull(message = "training id tidak boleh null")
    private Long trainingId;

    @NotNull(message = "tanggal training tidak boleh null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggalTraining;
}
