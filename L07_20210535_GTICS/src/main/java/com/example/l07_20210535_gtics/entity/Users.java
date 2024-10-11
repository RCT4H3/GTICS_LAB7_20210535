package com.example.l07_20210535_gtics.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "id_user", nullable = false)
    private Integer Id;

    @Column(name = "email", nullable = false)
    private String Email;

    @Column(name = "password", nullable = false)
    private String Password;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "activo", nullable = false)
    private Boolean Activo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Roles roles;
}
