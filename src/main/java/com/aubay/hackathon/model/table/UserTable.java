package com.aubay.hackathon.model.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nameDescription;

    @NotNull
    private String companyName;

    @NotNull
    private String location;

    @NotNull
    @Column(unique = true)
    private String email;

    private String phone;

    @NotNull
    private String password;

    @NotNull
    private boolean userStatus;

    @NotNull
    private Long role;

    private Long access;
}
