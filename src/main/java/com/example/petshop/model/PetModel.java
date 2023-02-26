package com.example.petshop.model;

import com.example.petshop.status.Treatment;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "TB_PET")
public class PetModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String petName;
    private String specie;
    private String breed;
    private String height;
    private String weight;
    private String coatType;
    private Treatment treatment;
    private String tutorName;
    private String tutorCel;



}
