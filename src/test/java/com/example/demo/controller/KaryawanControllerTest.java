package com.example.demo.controller;

import com.example.demo.entity.Karyawan;
import com.example.demo.model.KaryawanRequest;
import com.example.demo.model.KaryawanRequestUpdate;
import com.example.demo.model.WebResponse;
import com.example.demo.repository.KaryawanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;

@SpringBootTest
@AutoConfigureMockMvc
class KaryawanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @BeforeEach
    void setUp() {
        karyawanRepository.deleteAll();
    }

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void testUpdateKaryawan() throws Exception {
        KaryawanRequest karyawanRequest = KaryawanRequest.builder()
                .nama("Abede Rahman")
                .dob(LocalDate.parse("24/12/1996", format))
                .jk("Laki-laki")
                .nik("7315111111111111")
                .npwp("123456789012345")
                .alamat("Jalan Belum ada")
                .status("lajang")
                .build();

        Karyawan karyawan = karyawanRepository.save(karyawanRequest.buildKaryawan());
        Long id = karyawan.getId();
        KaryawanRequestUpdate karyawanRequestUpdate = KaryawanRequestUpdate.builder()
                .id(id)
                .alamat("Jalan Belum jadi")
                .nik("1234567890123456")
                .jk("Laki-laki")
                .npwp("123456789012345")
                .nama("Gon Freecss")
                .status("lajang")
                .dob(LocalDate.parse("11/11/2011", format))
                .build();

        mockMvc.perform(
                put("/v1/karyawan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(karyawanRequestUpdate))
        ).andExpectAll(
                status().isOk()
        ).andDo(new ResultHandler() {
            @Override
            public void handle(MvcResult result) throws Exception {
                String contentAsString = result.getResponse().getContentAsString();
                WebResponse<Karyawan> webResponse = objectMapper.readValue(contentAsString, new TypeReference<WebResponse<Karyawan>>() {
                });
                Assertions.assertEquals(karyawan.getId(), webResponse.getData().getId());
                Assertions.assertEquals("Gon Freecss", webResponse.getData().getNama());
            }
        });
    }

    @Test
    void testInsertFailed() throws Exception {
        KaryawanRequest karyawanRequest = KaryawanRequest.builder()
                .nama("Abede Rahman")
                .dob(LocalDate.parse("24/12/1996", format))
                .jk("Laki-laki")
                .nik("7315111111111111")
                .npwp("ini npwp")
                .alamat("Jalan Belum ada")
                .status("lajang")
                .build();

        mockMvc.perform(
                post("/v1/karyawan")
                        .content(objectMapper.writeValueAsString(karyawanRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result ->{
            WebResponse<Karyawan> response = objectMapper.readValue(result.getResponse().getContentAsString(), WebResponse.class);
            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void testInsertSuccess() throws Exception {
        KaryawanRequest karyawanRequest = KaryawanRequest.builder()
                .nama("Abede Rahman")
                .dob(LocalDate.parse("24/12/1996", format))
                .jk("Laki-laki")
                .nik("7315111111111111")
                .npwp("123456789012345")
                .alamat("Jalan Belum ada")
                .status("lajang")
                .build();

        mockMvc.perform(
                post("/v1/karyawan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(karyawanRequest))
        ).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON)
        ).andDo(result ->{
            //buat object web response
            WebResponse<Karyawan> karyawanWebResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<Karyawan>>() {
                    });
            Assertions.assertNotNull(karyawanWebResponse.getData().getId());
                }
        );
    }
}