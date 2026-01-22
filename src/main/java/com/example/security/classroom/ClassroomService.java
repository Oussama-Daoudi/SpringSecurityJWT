package com.example.security.classroom;

import com.example.security.student.Student;
import com.example.security.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentRepository studentRepository; // Add this injection

    // ➕ Ajouter une classroom
    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    // 🔍 Trouver par ID
    public Classroom findById(Integer id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
    }

    // ✏️ Modifier une classroom
    public Classroom update(Integer id, Classroom newClassroom) {
        Classroom oldClassroom = findById(id);

        oldClassroom.setName(newClassroom.getName());
        oldClassroom.setStudents(newClassroom.getStudents());

        return classroomRepository.save(oldClassroom);
    }

    // 📌 Récupérer toutes les classrooms
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    // ❌ Supprimer
    public void delete(Integer id) {
        classroomRepository.deleteById(id);
    }

    // 1️⃣ Best Classroom
    public Classroom getBestClassroom() {
        return classroomRepository.getBestClassroom();
    }

    // 2️⃣ Nombre d'étudiants participant à des clubs par classe
    public List<Object[]> countStudentsInClubsByClassroom() {
        return classroomRepository.countStudentsInClubsByClassroom();
    }

    // 3️⃣ Liste des étudiants participant à des clubs par classe
    public List<Object[]> getStudentsInClubsByClassroom() {
        return classroomRepository.getStudentsInClubsByClassroom();
    }

    // 4️⃣ NEW METHOD: Get students in clubs by specific classroom ID
    public List<Student> getStudentsInClubsByClass(Integer classroomId) {
        // Method 1: Using the query method (recommended)
        return studentRepository.findByClassroomIdAndClubsNotEmpty(classroomId);

        // Method 2: Alternative with @Query annotation (uncomment if you prefer)
        // return studentRepository.findStudentsInClubsByClassroomId(classroomId);
    }
}