/*
 * package com.ReactFrom.Service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.ReactFrom.Model.StudentD; import
 * com.ReactFrom.Repository.StudentRepository;
 * 
 * 
 * @Service public class StudentServiceImpl implements StudentService {
 * 
 * @Autowired private StudentRepository studentRepository;
 * 
 * 
 * @Override public StudentD saveStudent(StudentD student) { return
 * studentRepository.save(student); }
 * 
 * @Override public List<StudentD> getAllStudent() { return
 * studentRepository.findAll(); }
 * 
 * }
 */


package com.ReactFrom.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReactFrom.Model.StudentD;
import com.ReactFrom.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentD saveStudent(StudentD student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentD> getAllStudent() {
        return studentRepository.findAll();
    }

    // Get student by ID
    @Override
    public Optional<StudentD> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update student
    @Override
    public StudentD updateStudent(Long id, StudentD updatedStudent) {

        StudentD student = studentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Student not found with ID: " + id));

        student.setFirstname(updatedStudent.getFirstname());
        student.setLastname(updatedStudent.getLastname());
        student.setEmail(updatedStudent.getEmail());
        student.setNumber(updatedStudent.getNumber());
        student.setContact(updatedStudent.getContact());
        student.setGender(updatedStudent.getGender());
        student.setLanguages(updatedStudent.getLanguages());
        student.setSubject(updatedStudent.getSubject());
        student.setResumeName(updatedStudent.getResumeName());
        student.setDate(updatedStudent.getDate());
        student.setAbout(updatedStudent.getAbout());

        return studentRepository.save(student);
    }

    // Delete student
    @Override
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException(
                "Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);
    }
}
