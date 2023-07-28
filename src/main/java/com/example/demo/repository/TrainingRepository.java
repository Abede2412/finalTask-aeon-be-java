package com.example.demo.repository;

import com.example.demo.entity.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    Page<Training> findByTemaLikeIgnoreCase(String s, Pageable pageable);
}

