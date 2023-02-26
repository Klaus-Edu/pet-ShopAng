package com.example.petshop.repo;

import com.example.petshop.model.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PetRepo extends JpaRepository<PetModel, Long> {

}
