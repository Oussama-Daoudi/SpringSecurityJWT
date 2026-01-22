package com.example.security.student;

import com.example.security.classroom.Classroom;
import com.example.security.club.Club;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nsc;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    @JsonIgnoreProperties("students")
    private Classroom classroom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "club_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    @JsonIgnoreProperties("students")
    private List<Club> clubs;
    public List<Club> getGroupes() { return clubs; }
    public void setGroupes(List<Club> groupes) { this.clubs = groupes; }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNsc() {
        return nsc;
    }

    public void setNsc(String nsc) {
        this.nsc = nsc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}

