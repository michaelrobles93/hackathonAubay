package com.aubay.hackathon.model.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "ROLE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleTable {

    @Id
    private Long id;

    @NotNull
    private String description;
}
