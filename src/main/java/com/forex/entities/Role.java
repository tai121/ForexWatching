package com.forex.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;




    @Column(name="isdeleted",columnDefinition = "boolean default false")
    private boolean isdeleted;

    public boolean isIsdeleted() {
        return isdeleted;
    }

    @Column(name = "createAt",nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "updateAt",nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();
    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
