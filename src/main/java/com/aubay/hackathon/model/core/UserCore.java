package com.aubay.hackathon.model.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCore {

    private Long id;

    private String nameDescription;

    private String companyName;

    private String location;

    private String email;

    private String phone;

    private boolean userStatus;

    private String password;

    private Long role;

    private Long access;
}
