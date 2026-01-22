package com.example.security.student;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    //  Nombre TOTAL d’étudiants
    @Query("SELECT COUNT(s) FROM Student s")
    long countStudents();

    //  Nombre d’étudiants participants dans des clubs
    @Query("SELECT COUNT(DISTINCT s) FROM Student s JOIN s.clubs c")
    long countStudentsInClubs();

    // Meilleur étudiant
    @Query("""
           SELECT s FROM Student s 
           WHERE SIZE(s.clubs) = (
               SELECT MAX(SIZE(st.clubs)) FROM Student st
           )
           """)
    Student getBestStudent();

    //  Liste des clubs d’un étudiant donné
    @Query("SELECT c.ref FROM Student s JOIN s.clubs c WHERE s.id = :id")
    List<String> getClubsOfStudent(Integer id);
    List<Student> findByClassroomIdAndClubsNotEmpty(Integer classroomId);

}
