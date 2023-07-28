package com.example.demo.service;

import com.example.demo.entity.Training;
import com.example.demo.model.ParameterRequest;
import com.example.demo.model.TrainingRequest;
import com.example.demo.model.TrainingRequestUpdate;
import com.example.demo.model.WebResponse;
import com.example.demo.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ValidationService validationService;

    public Training insert(TrainingRequest trainingRequest){
        validationService.validate(trainingRequest);
        Training training = trainingRequest.convertToEntity();
        return trainingRepository.save(training);
    }

    public Training update(TrainingRequestUpdate trainingRequestUpdate){
        validationService.validate(trainingRequestUpdate);
        Training training = getById(trainingRequestUpdate.getId());
        training.setTema(trainingRequestUpdate.getTema());
        training.setNamaPengajar(trainingRequestUpdate.getNamaPengajar());
        return trainingRepository.save(training);
    }

    public Training getById(Long id){
        return trainingRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Training dengan id "+id+" tidak ditemukan"
            );
        });
    }

    public Page<Training> getListTraining(ParameterRequest request){
        validationService.validate(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Training> trainingList = trainingRepository.findByTemaLikeIgnoreCase("%"+request.getNamaTraining()+"%", pageable);

        return new PageImpl<>(trainingList.getContent(), pageable, trainingList.getTotalElements());
    }


}
