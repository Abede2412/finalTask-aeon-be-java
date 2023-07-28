package com.example.demo.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterRequest {

    private String namaKaryawan;

    private String namaTraining;

    @PositiveOrZero(message = "page harus positif atau 0")
    private Integer page;

    @Positive(message = "size harus positif")
    private Integer size;

    public void lowercase(){
        if (Objects.nonNull(namaKaryawan)){
            setNamaKaryawan(namaKaryawan.toLowerCase());
        }

        if (Objects.nonNull(namaTraining)){
            setNamaTraining(namaTraining.toLowerCase());
        }
    }
}
