package com.example.security.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    //   max d’étudiants participant à des clubs
    @Query("""
           SELECT c 
           FROM Classroom c 
           JOIN c.students s 
           WHERE SIZE(s.clubs) = (
               SELECT MAX(SIZE(st.clubs)) 
               FROM Classroom cl JOIN cl.students st
           )
           """)
    Classroom getBestClassroom();

    // Nombre d'étudiants participant à des clubs par classe
    @Query("""
           SELECT c.name, COUNT(DISTINCT s)
           FROM Classroom c 
           JOIN c.students s 
           JOIN s.clubs cl
           GROUP BY c.name
           """)
    List<Object[]> countStudentsInClubsByClassroom();

    //  Liste des étudiants participant à des clubs par classe
    @Query("""
           SELECT c.name, s 
           FROM Classroom c 
           JOIN c.students s 
           WHERE SIZE(s.clubs) > 0
           """)
    List<Object[]> getStudentsInClubsByClassroom();
}
