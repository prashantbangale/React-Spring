/*
 * package com.ReactFrom.Controller;
 * 
 * import java.util.List; import org.springframework.web.bind.annotation.*;
 * 
 * import com.ReactFrom.Model.StudentD; import
 * com.ReactFrom.Repository.StudentRepository;
 * 
 * @RestController
 * 
 * @RequestMapping("/student")
 * 
 * @CrossOrigin(origins = "http://localhost:5173") public class
 * StudentController {
 * 
 * private final StudentRepository repo;
 * 
 * public StudentController(StudentRepository repo) { this.repo = repo; }
 * 
 * @PostMapping("/add") public StudentD addStudent(@RequestBody StudentD
 * student) { return repo.save(student); }
 * 
 * @GetMapping("/getAll") public List<StudentD> getAllStudents() { return
 * repo.findAll(); } }
 */


package com.ReactFrom.Controller;



import org.springframework.web.bind.annotation.*;

import com.ReactFrom.Model.StudentD;
import com.ReactFrom.Repository.StudentRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    @GetMapping("/getAll")
    public List<StudentD> getAllStudents() {
        return studentRepository.findAll();
    }

    // Add student
    @PostMapping("/add")
    public StudentD addStudent(@RequestBody StudentD student) {
        return studentRepository.save(student);
    }

    // Get student by ID for editing
    @GetMapping("/{id}")
    public ResponseEntity<StudentD> getStudentById(
            @PathVariable Long id) {

        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update student
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentD updatedStudent) {

        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(404)
                    .body("Student not found with ID: " + id);
        }

        StudentD student = studentRepository.findById(id).get();

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

        StudentD savedStudent = studentRepository.save(student);

        return ResponseEntity.ok(savedStudent);
    }
    
    

    // Delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
