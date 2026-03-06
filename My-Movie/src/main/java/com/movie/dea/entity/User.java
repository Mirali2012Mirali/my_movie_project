package com.movie.dea.entity;

import jakarta.persistence.*;

import javax.management.relation.Role;

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
//        @Column(nullable = false)
//        private String role;
        @Column(nullable = false, unique = true, length = 15)
        private String phone;

        public Integer getId() {
        return id;
    }

        public void setId(Integer id) {
            this.id = id;
        }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

