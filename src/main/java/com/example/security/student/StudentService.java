package com.example.security.student;

import com.example.security.club.Club;
import com.example.security.club.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClubRepository clubRepository;

    // ➕ Ajouter un étudiant
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // 🔍 Trouver par ID
    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // ✏️ Modifier un étudiant
    public Student update(Integer id, Student newStudent) {
        Student oldStudent = findById(id);

        oldStudent.setNsc(newStudent.getNsc());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setClassroom(newStudent.getClassroom());
        oldStudent.setClubs(newStudent.getClubs());

        return studentRepository.save(oldStudent);
    }

    // 📄 Obtenir tous les étudiants
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // ❌ Supprimer
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    // ➕ Ajouter un club à un étudiant
    public Student addClub(Integer studentId, Integer clubId) {
        Student student = findById(studentId);
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        student.getClubs().add(club);
        club.getStudents().add(student);

        clubRepository.save(club);
        return studentRepository.save(student);
    }

    // ➖ Retirer un club
    public Student removeClub(Integer studentId, Integer clubId) {
        Student student = findById(studentId);
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        student.getClubs().remove(club);
        club.getStudents().remove(student);

        clubRepository.save(club);
        return studentRepository.save(student);
    }

    // Statistiques
    public long countStudents() {
        return studentRepository.countStudents();
    }

    public long countStudentsInClubs() {
        return studentRepository.countStudentsInClubs();
    }

    public Student getBestStudent() {
        return studentRepository.getBestStudent();
    }

    public List<String> getClubsOfStudent(Integer id) {
        return studentRepository.getClubsOfStudent(id);
    }
}
