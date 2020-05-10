package com.aubay.hackathon.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRequest {

    @NotBlank
    private String nameDescription;

    @NotBlank
    private String companyName;

    @NotBlank
    private String location;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String phone;

}
