package com.example.demo.service;

import com.example.demo.entity.Karyawan;
import com.example.demo.entity.KaryawanTraining;
import com.example.demo.entity.Training;
import com.example.demo.model.KaryawanTrainingRequest;
import com.example.demo.model.ParameterRequest;
import com.example.demo.repository.KaryawanTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanTrainingService {

    @Autowired
    private KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private KaryawanService karyawanService;

    public Page<KaryawanTraining> filter(ParameterRequest request){
        validationService.validate(request);
        request.lowercase();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<KaryawanTraining> karyawanTrainingPage = karyawanTrainingRepository
                .findAllByFilter(request.getNamaTraining(), request.getNamaKaryawan(), pageable);

        return new PageImpl<>(karyawanTrainingPage.getContent(),pageable,karyawanTrainingPage.getTotalElements());

    }

    public KaryawanTraining insert(KaryawanTrainingRequest request){
        validationService.validate(request);

        Karyawan karyawan = karyawanService.getById(request.getKaryawanId());
        Training training = trainingService.getById(request.getTrainingId());

        KaryawanTraining karyawanTraining = KaryawanTraining.builder()
                .karyawan(karyawan)
                .training(training)
                .tanggalTraining(request.getTanggalTraining())
                .build();

        return karyawanTrainingRepository.save(karyawanTraining);
    }
}