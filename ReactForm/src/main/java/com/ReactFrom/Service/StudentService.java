/*
 * package com.ReactFrom.Service;
 * 
 * import java.util.List;
 * 
 * import com.ReactFrom.Model.StudentD;
 * 
 * 
 * public interface StudentService { public StudentD saveStudent(StudentD
 * student); public List<StudentD> getAllStudent(); }
 */


package com.ReactFrom.Service;

import java.util.List;
import java.util.Optional;

import com.ReactFrom.Model.StudentD;

public interface StudentService {

    StudentD saveStudent(StudentD student);

    List<StudentD> getAllStudent();

    Optional<StudentD> getStudentById(Long id);

    StudentD updateStudent(Long id, StudentD student);

    void deleteStudent(Long id);
}

