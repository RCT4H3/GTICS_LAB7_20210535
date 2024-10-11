package com.example.l07_20210535_gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "id_rol", nullable = false)
    private Integer Id;

    @Column(name = "name", nullable = false)
    private String Name;

}
