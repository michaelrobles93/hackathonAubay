package com.aubay.hackathon.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResponse {

    private Long id;

    private String nameDescription;

    private String companyName;

    private String location;

    private String email;

    private String phone;

    private boolean userStatus;

    private Role role;

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Role {

        private Long id;

        private String descricao;
    }

}