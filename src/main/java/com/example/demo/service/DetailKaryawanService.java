package com.example.demo.service;

import com.example.demo.repository.DetailKaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailKaryawanService {

    @Autowired
    private DetailKaryawanRepository detailKaryawanRepository;
}
