package com.tss.survey.repository;

import com.tss.survey.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Trail entities.
 *
 * Extends JpaRepository to provide built-in CRUD operations
 * such as save, findAll, findById, delete, etc.
 */
public interface TrailRepository extends JpaRepository<Trail, Integer> {
}