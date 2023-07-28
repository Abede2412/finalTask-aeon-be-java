package com.example.demo.controller;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.example.demo.entity.Training;
import com.example.demo.model.*;
import com.example.demo.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping(
            path = "/v1/training",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Training> insert(@RequestBody TrainingRequest trainingRequest){
        Training training = trainingService.insert(trainingRequest);
        return WebResponse.<Training>builder().data(training).build();
    }

    @PutMapping(
            path = "/v1/training",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Training> update(@RequestBody TrainingRequestUpdate trainingRequestUpdate){
        Training training = trainingService.update(trainingRequestUpdate);
        return WebResponse.<Training>builder().data(training).build();
    }

    @GetMapping(
            path = "/v1/training/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Training> GetById(@PathVariable("id") Long id){
        Training training = trainingService.getById(id);
        return WebResponse.<Training>builder().data(training).build();
    }

    @GetMapping(
            path = "/v1/training/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<Training>> GetListByTema(
            @RequestParam(value = "tema", required = false) String tema,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ){
        ParameterRequest request = new ParameterRequest();
        request.setNamaTraining(tema);
        request.setPage(page);
        request.setSize(size);

        Page<Training> listTraining = trainingService.getListTraining(request);
        return WebResponse.<List<Training>>builder()
                .paging(Paging.builder()
                        .currentPage(request.getPage())
                        .size(request.getSize())
                        .totalPage(listTraining.getTotalPages())
                        .build())
                .data(listTraining.getContent())
                .build();
    }



}
