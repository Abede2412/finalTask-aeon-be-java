package com.example.demo.model;

import com.example.demo.entity.Training;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequest {

    @NotBlank(message = "Nama pengajar tidak boleh kosong")
    private String namaPengajar;

    @NotBlank(message = "Tema pelatihan tidak boleh kosong")
    private String tema;

    public Training convertToEntity(){
        return Training.builder()
                .namaPengajar(namaPengajar)
                .tema(tema)
                .build();
    }

}
