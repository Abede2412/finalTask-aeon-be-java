package com.example.demo.service;

import com.example.demo.entity.Karyawan;
import com.example.demo.model.KaryawanRequest;
import com.example.demo.model.KaryawanRequestUpdate;
import com.example.demo.model.ParameterRequest;
import com.example.demo.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;
    @Autowired
    private ValidationService validationService;
    @Transactional
    public Karyawan insert(KaryawanRequest karyawanRequest){
        validationService.validate(karyawanRequest);
        Karyawan karyawan = karyawanRequest.buildKaryawan();
        return karyawanRepository.save(karyawan);
    }

    @Transactional
    public Karyawan update(KaryawanRequestUpdate karyawanRequest) {
        validationService.validate(karyawanRequest);
        Karyawan karyawan = getById(karyawanRequest.getId());
        karyawan.setNama(karyawanRequest.getNama());
        karyawan.setJk(karyawanRequest.getJk());
        karyawan.setDob(karyawanRequest.getDob());
        karyawan.setAlamat(karyawanRequest.getAlamat());
        karyawan.setStatus(karyawanRequest.getStatus());
        karyawan.getDetailKaryawan().setNik(karyawanRequest.getNik());
        karyawan.getDetailKaryawan().setNpwp(karyawanRequest.getNpwp());

        return karyawanRepository.save(karyawan);
    }

    public Karyawan getById(Long id){
        return karyawanRepository.findById(id)
                .orElseThrow(() ->{
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Karyawan dengan id "+id+" tidak ditemukan"
                            );
                });
    }

    public Page<Karyawan> getListKaryawan(ParameterRequest request){
        validationService.validate(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Karyawan> karyawanList = karyawanRepository.findByNamaLikeIgnoreCase("%"+request.getNamaKaryawan()+"%", pageable);
        return new PageImpl<>(karyawanList.getContent(),pageable,karyawanList.getTotalElements());
    }
}
