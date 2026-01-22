package com.example.security.student;

import com.example.security.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ApiResponse<Student> addStudent(@Valid @RequestBody Student student) {
        return ApiResponse.success(
                "Student added successfully",
                studentService.save(student)
        );
    }

    @GetMapping("/get/{id}")
    public ApiResponse<Student> getStudent(@PathVariable Integer id) {
        return ApiResponse.success(
                "Student retrieved successfully",
                studentService.findById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Student> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody Student student) {
        return ApiResponse.success(
                "Student updated successfully",
                studentService.update(id, student)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteStudent(@PathVariable Integer id) {
        studentService.delete(id);
        return ApiResponse.success(
                "Student deleted successfully",
                null
        );
    }

    @PutMapping("/{studentId}/addClub/{clubId}")
    public ApiResponse<Student> addClub(
            @PathVariable Integer studentId,
            @PathVariable Integer clubId) {
        return ApiResponse.success(
                "Club added to student successfully",
                studentService.addClub(studentId, clubId)
        );
    }

    @PutMapping("/{studentId}/removeClub/{clubId}")
    public ApiResponse<Student> removeClub(
            @PathVariable Integer studentId,
            @PathVariable Integer clubId) {
        return ApiResponse.success(
                "Club removed from student successfully",
                studentService.removeClub(studentId, clubId)
        );
    }

    @GetMapping("/count")
    public ApiResponse<Long> countStudents() {
        return ApiResponse.success(
                "Student count retrieved successfully",
                studentService.countStudents()
        );
    }

    @GetMapping("/count/in-clubs")
    public ApiResponse<Long> countStudentsInClubs() {
        return ApiResponse.success(
                "Students in clubs count retrieved successfully",
                studentService.countStudentsInClubs()
        );
    }

    @GetMapping("/best")
    public ApiResponse<Student> getBestStudent() {
        return ApiResponse.success(
                "Best student retrieved successfully",
                studentService.getBestStudent()
        );
    }

    @GetMapping("/{id}/clubs")
    public ApiResponse<List<String>> getClubs(@PathVariable Integer id) {
        return ApiResponse.success(
                "Student's clubs retrieved successfully",
                studentService.getClubsOfStudent(id)
        );
    }

    // Optional: Add this if you want to get all students
    @GetMapping("/all")
    public ApiResponse<List<Student>> getAllStudents() {
        return ApiResponse.success(
                "All students retrieved successfully",
                studentService.findAll()
        );
    }
}