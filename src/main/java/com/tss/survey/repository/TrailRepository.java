package com.tss.survey.repository;

import com.tss.survey.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailRepository extends JpaRepository<Trail, Integer> {
}