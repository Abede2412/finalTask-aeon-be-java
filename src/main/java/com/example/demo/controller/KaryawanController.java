package com.example.demo.controller;

import com.example.demo.entity.Karyawan;
import com.example.demo.model.*;
import com.example.demo.service.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    @PostMapping(
            path = "/v1/karyawan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Karyawan> insert(@RequestBody KaryawanRequest karyawanRequest){
        Karyawan karyawan = karyawanService.insert(karyawanRequest);
        return WebResponse.<Karyawan>builder().data(karyawan).build();
    }

    @PutMapping(
            path = "/v1/karyawan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Karyawan> update(@RequestBody KaryawanRequestUpdate karyawanRequest){
        Karyawan karyawan = karyawanService.update(karyawanRequest);
        return WebResponse.<Karyawan>builder().data(karyawan).build();
    }

    @GetMapping(
            path = "/v1/karyawan/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Karyawan> getById(@PathVariable("id") Long id){
        Karyawan karyawan = karyawanService.getById(id);
        return WebResponse.<Karyawan>builder().data(karyawan).build();
    }

    @GetMapping(
            path = "v1/karyawan/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<Karyawan>> getListKaryawan(@RequestParam(value = "nama", required = false) String name,
                                                       @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10", required = false) Integer size
    ){
        ParameterRequest request = ParameterRequest.builder()
                .namaKaryawan(name)
                .page(page)
                .size(size)
                .build();

        Page<Karyawan> listKaryawan = karyawanService.getListKaryawan(request);
        return WebResponse.<List<Karyawan>>builder()
                .data(listKaryawan.getContent())
                .paging(Paging.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage(listKaryawan.getTotalPages())
                        .build())
                .build();
    }
}
