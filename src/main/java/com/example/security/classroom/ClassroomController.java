package com.example.security.classroom;

import com.example.security.ApiResponse;
import com.example.security.student.Student; // Correct import
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/add")
    public ApiResponse<Classroom> addClassroom(@Valid @RequestBody Classroom classroom) {
        return ApiResponse.success(
                "Classroom added successfully",
                classroomService.save(classroom)
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Classroom> updateClassroom(
            @PathVariable Integer id,
            @Valid @RequestBody Classroom classroom) {
        return ApiResponse.success(
                "Classroom updated successfully",
                classroomService.update(id, classroom)
        );
    }

    @GetMapping("/get/{id}")
    public ApiResponse<Classroom> getClassroom(@PathVariable Integer id) {
        return ApiResponse.success(
                "Classroom retrieved successfully",
                classroomService.findById(id)
        );
    }

    @GetMapping("/all")
    public ApiResponse<List<Classroom>> getAllClassrooms() {
        return ApiResponse.success(
                "Classrooms retrieved successfully",
                classroomService.findAll()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteClassroom(@PathVariable Integer id) {
        classroomService.delete(id);
        return ApiResponse.success(
                "Classroom deleted successfully",
                null
        );
    }

    @GetMapping("/best")
    public ApiResponse<Classroom> getBestClassroom() {
        return ApiResponse.success(
                "Best classroom retrieved successfully",
                classroomService.getBestClassroom()
        );
    }

    @GetMapping("/students/count-in-clubs")
    public ApiResponse<List<Object[]>> countStudentsInClubsByClassroom() {
        return ApiResponse.success(
                "Students in clubs count by classroom retrieved successfully",
                classroomService.countStudentsInClubsByClassroom()
        );
    }

    @GetMapping("/students/list-in-clubs")
    public ApiResponse<List<Object[]>> getStudentsInClubs() {
        return ApiResponse.success(
                "Students in clubs retrieved successfully",
                classroomService.getStudentsInClubsByClassroom()
        );
    }

    // NEW ENDPOINT - Fixed with correct import
    @GetMapping("/{id}/students/in-clubs")
    public ApiResponse<List<Student>> getStudentsInClubsByClass(@PathVariable Integer id) {
        return ApiResponse.success(
                "Students in clubs by class retrieved successfully",
                classroomService.getStudentsInClubsByClass(id)
        );
    }
}