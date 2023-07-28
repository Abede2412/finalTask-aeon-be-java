package com.example.demo.controller;

import com.example.demo.entity.KaryawanTraining;
import com.example.demo.model.KaryawanTrainingRequest;
import com.example.demo.model.Paging;
import com.example.demo.model.ParameterRequest;
import com.example.demo.model.WebResponse;
import com.example.demo.service.KaryawanTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KaryawanTrainingController {

    @Autowired
    private KaryawanTrainingService karyawanTrainingService;

    @PostMapping(path = "/v1/training-karyawan")
    public WebResponse<KaryawanTraining> insert(@RequestBody KaryawanTrainingRequest request){
       KaryawanTraining karyawanTraining = karyawanTrainingService.insert(request);
       return WebResponse.<KaryawanTraining>builder()
               .data(karyawanTraining).build();
    }

    @GetMapping(path = "/v1/training-karyawan/list")
    public WebResponse<List<KaryawanTraining>> filter(@RequestParam(name = "nama-karyawan", required = false) String namaKaryawan,
                                                      @RequestParam(name = "tema-training", required = false) String temaTraining,
                                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ){
        ParameterRequest parameterRequest = ParameterRequest.builder()
                .namaKaryawan(namaKaryawan)
                .namaTraining(temaTraining)
                .size(size)
                .page(page)
                .build();

        Page<KaryawanTraining> karyawanTrainingPage = karyawanTrainingService.filter(parameterRequest);
        return WebResponse.<List<KaryawanTraining>>builder()
                .data(karyawanTrainingPage.getContent())
                .paging(Paging.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage(karyawanTrainingPage.getTotalPages())
                        .build())
                .build();
    }
}
