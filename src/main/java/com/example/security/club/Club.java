package com.example.security.club;

import com.example.security.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

    @Entity
    @Table(name = "Club",schema = "default")
    public class Club {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "ref", nullable = false, unique = true)
        private String ref;

        private String dateCreation;


        @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
        @JsonIgnoreProperties("clubs")
        private List<Student> students;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getDateCreation() {
            return dateCreation;
        }

        public void setDateCreation(String dateCreation) {
            this.dateCreation = dateCreation;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }
