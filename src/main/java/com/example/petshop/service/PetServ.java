package com.example.petshop.service;

import com.example.petshop.dtos.PetDto;
import com.example.petshop.model.PetModel;
import com.example.petshop.repo.PetRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServ {

    @Autowired
    private final PetRepo petRepo;

    public PetServ(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    @Transactional
    public PetModel savePetOrder(PetModel petModel) {
        return petRepo.save(petModel);
    }

    public List<PetModel> findAll() {
        return petRepo.findAll();
    }

    @Transactional
    public void delete(PetModel petModel) {
        petRepo.delete(petModel);
    }

    public Optional<PetModel> findById(Long id) {
        return petRepo.findById(id);
    }

    public PetModel updatePet(PetModel petModel) {
        return petRepo.save(petModel);
    }
}
