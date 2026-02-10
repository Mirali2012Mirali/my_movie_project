package com.movie.dea.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")


public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(unique = true, nullable = false) // "" null
        private String username;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false)
        private String role;

        public Integer getId() {
        return id;
    }

        public void setId(Integer id) {
            this.id = id;
        }

    public String getRole() {
        return role;
    }

        public void setRole(String role) {
            this.role = role;
        }
    }

