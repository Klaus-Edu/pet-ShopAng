package com.example.petshop.dtos;

import com.example.petshop.status.Treatment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetDto {

    @NotBlank
    private String petName;
    @NotBlank
    private String specie;
    @NotBlank
    private String breed;
    @NotBlank
    private String height;
    @NotBlank
    private String weight;
    @NotBlank
    private String coatType;
    @NotNull
    private Treatment treatment;
    @NotBlank
    private String tutorName;
    @NotBlank
    private String tutorCel;
}
