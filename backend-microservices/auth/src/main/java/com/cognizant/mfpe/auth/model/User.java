package com.cognizant.mfpe.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "username" })}
)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String username;

    private String password;

    public User(String name, String username, String password) {
        super();
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
