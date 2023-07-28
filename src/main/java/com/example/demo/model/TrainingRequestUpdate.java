package com.example.demo.model;

import com.example.demo.entity.Training;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequestUpdate {

    @NotNull(message = "id tidak boleh null")
    private Long id;

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
