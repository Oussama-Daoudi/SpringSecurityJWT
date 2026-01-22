package com.example.security.club;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    // ➕ Ajouter un club
    public Club save(Club club) {
        return clubRepository.save(club);
    }

    // 🔍 Trouver un club par ID
    public Club findById(Integer id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));
    }

    // ✏️ Modifier un club
    public Club update(Integer id, Club newClub) {
        Club oldClub = findById(id);

        oldClub.setRef(newClub.getRef());
        oldClub.setDateCreation(newClub.getDateCreation());
        oldClub.setStudents(newClub.getStudents()); // ManyToMany

        return clubRepository.save(oldClub);
    }

    // 📌 Récupérer tous les clubs
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    // ❌ Supprimer un club
    public void delete(Integer id) {
        clubRepository.deleteById(id);
    }

    // 1️⃣ Nombre total de clubs
    public long countClubs() {
        return clubRepository.countClubs();
    }

    // 2️⃣ Best clubs
    public List<Club> getBestClubs() {
        return clubRepository.getBestClubs();
    }

    // 3️⃣ Worst clubs
    public List<Club> getWorstClubs() {
        return clubRepository.getWorstClubs();
    }

    // 4️⃣ Nombre d’étudiants dans un club donné
    public int countStudentsInClub(Integer id) {
        return clubRepository.countStudentsInClub(id);
    }

    // 5️⃣ Nombre d’étudiants par club
    public List<Object[]> studentsCountByClub() {
        return clubRepository.studentsCountByClub();
    }
}
