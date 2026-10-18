package com.ReactFrom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ReactFrom.Model.StudentD;

public interface StudentRepository
        extends JpaRepository<StudentD, Long> {
}