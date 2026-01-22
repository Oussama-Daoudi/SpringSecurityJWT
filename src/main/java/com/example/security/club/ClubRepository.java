package com.example.security.club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    //  Nombre total de clubs
    @Query("SELECT COUNT(c) FROM Club c")
    long countClubs();

    // 2️⃣ Clubs avec le nombre maximum d’étudiants
    @Query("""
           SELECT c FROM Club c 
           WHERE SIZE(c.students) = (
               SELECT MAX(SIZE(cl.students)) FROM Club cl
           )
           """)
    List<Club> getBestClubs(); // ⚠ retourne une liste

    // 3️⃣ Clubs avec le nombre minimum d’étudiants
    @Query("""
           SELECT c FROM Club c 
           WHERE SIZE(c.students) = (
               SELECT MIN(SIZE(cl.students)) FROM Club cl
           )
           """)
    List<Club> getWorstClubs(); // ⚠ retourne une liste

    // 4️⃣ Nombre d’étudiants d’un club donné
    @Query("SELECT SIZE(c.students) FROM Club c WHERE c.id = :id")
    int countStudentsInClub(Integer id);

    // 5️⃣ Nombre d’étudiants par club (liste)
    @Query("SELECT c.ref, SIZE(c.students) FROM Club c")
    List<Object[]> studentsCountByClub();


    // Liste de Clubs d’un étudiant donné  (déjà un attribut ds la classe Student

}
