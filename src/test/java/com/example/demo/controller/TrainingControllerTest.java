package com.example.demo.controller;

import com.example.demo.entity.Training;
import com.example.demo.model.TrainingRequest;
import com.example.demo.model.TrainingRequestUpdate;
import com.example.demo.model.WebResponse;
import com.example.demo.repository.TrainingRepository;
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

import java.lang.reflect.Type;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingControllerTest {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        trainingRepository.deleteAll();
    }

    @Test
    void getTrainingByIdFailed() throws Exception {
        mockMvc.perform(
                get("/v1/training/1000")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<Training> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<Training>>() {});
            Assertions.assertNotNull(response.getErrors());
        });
    }
    @Test
    void getTrainingByIdSuccess() throws Exception {
        TrainingRequest request = new TrainingRequest("Pak Eko", "Backend");
        Training training = trainingRepository.save(request.convertToEntity());

        mockMvc.perform(
                get("/v1/training/$id".replace("$id", training.getId().toString()))
        ).andExpectAll(
                status().isOk()
        ).andDo(result ->{
            WebResponse<Training> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
            Assertions.assertEquals(training.getId(), response.getData().getId());
            Assertions.assertEquals("Pak Eko", response.getData().getNamaPengajar());
        });
    }

    @Test
    void updateTrainingSucces() throws Exception {
        TrainingRequest request = new TrainingRequest("Pak Eko", "Backend");
        Training training = trainingRepository.save(request.convertToEntity());

        TrainingRequestUpdate requestUpdate = new TrainingRequestUpdate();
        requestUpdate.setId(training.getId());
        requestUpdate.setNamaPengajar("Eko Kurniawan Khannedy");
        requestUpdate.setTema("Backend With Spring");

        mockMvc.perform(
                put("/v1/training")
                        .content(objectMapper.writeValueAsString(requestUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result ->{
            WebResponse<Training> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
            Assertions.assertEquals(training.getId(), response.getData().getId());
            Assertions.assertEquals("Eko Kurniawan Khannedy", response.getData().getNamaPengajar());
        });
    }

    @Test
    void testInsertTrainingSucces() throws Exception {
        TrainingRequest request = new TrainingRequest("Pak Eko", "Backend");

        mockMvc.perform(
                post("/v1/training")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()

        ).andDo(result ->{
            WebResponse<Training> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
            Assertions.assertNotNull(response.getData().getId());
        });
    }
}