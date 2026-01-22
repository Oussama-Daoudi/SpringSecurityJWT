package com.example.security.club;

import com.example.security.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping
    public ApiResponse<Club> addClub(@Valid @RequestBody Club club) {
        return ApiResponse.success(
                "Club added successfully",
                clubService.save(club)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Club> updateClub(
            @PathVariable Integer id,
            @Valid @RequestBody Club club) {
        return ApiResponse.success(
                "Club updated successfully",
                clubService.update(id, club)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Club> getClub(@PathVariable Integer id) {
        return ApiResponse.success(
                "Club retrieved successfully",
                clubService.findById(id)
        );
    }

    @GetMapping
    public ApiResponse<List<Club>> getAllClubs() {
        return ApiResponse.success(
                "All clubs retrieved successfully",
                clubService.findAll()
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteClub(@PathVariable Integer id) {
        clubService.delete(id);
        return ApiResponse.success(
                "Club deleted successfully",
                null
        );
    }

    @GetMapping("/count")
    public ApiResponse<Long> countClubs() {
        return ApiResponse.success(
                "Club count retrieved successfully",
                clubService.countClubs()
        );
    }

    @GetMapping("/best")
    public ApiResponse<List<Club>> bestClubs() {
        return ApiResponse.success(
                "Best clubs retrieved successfully",
                clubService.getBestClubs()
        );
    }

    @GetMapping("/worst")
    public ApiResponse<List<Club>> worstClubs() {
        return ApiResponse.success(
                "Worst clubs retrieved successfully",
                clubService.getWorstClubs()
        );
    }

    @GetMapping("/{id}/students/count")
    public ApiResponse<Integer> countStudentsInClub(@PathVariable Integer id) {
        return ApiResponse.success(
                "Student count in club retrieved successfully",
                clubService.countStudentsInClub(id)
        );
    }

    @GetMapping("/students/count")
    public ApiResponse<List<Object[]>> studentsCountByClub() {
        return ApiResponse.success(
                "Student count by club retrieved successfully",
                clubService.studentsCountByClub()
        );
    }

    // Optional: Add endpoint to get students in a specific club
    // @GetMapping("/{id}/students")
    // public ApiResponse<List<com.example.security.student.Student>> getStudentsInClub(@PathVariable Integer id) {
    //     return ApiResponse.success(
    //             "Students in club retrieved successfully",
    //             clubService.getStudentsInClub(id)
    //     );
    // }
}