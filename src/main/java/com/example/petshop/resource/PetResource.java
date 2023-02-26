package com.example.petshop.resource;

import com.example.petshop.dtos.PetDto;
import com.example.petshop.model.PetModel;
import com.example.petshop.repo.PetRepo;
import com.example.petshop.service.PetServ;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/orders")
public class PetResource {

    @Autowired
    private final PetServ petServ;

    @Autowired
    private final PetRepo petRepo;

    public PetResource(PetServ petServ, PetRepo petRepo) {
        this.petServ = petServ;
        this.petRepo = petRepo;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> savePetOrder(@RequestBody @Valid PetDto petDto){
        PetModel petModel = new PetModel();

        BeanUtils.copyProperties(petDto, petModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(petServ.savePetOrder(petModel));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PetModel>> getAllPetOrders(){
        return new ResponseEntity<>(petServ.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getPetOrderById(@PathVariable(value = "id") Long id){
        Optional<PetModel> petModelOptional = petRepo.findById(id);

        if(!petModelOptional.isPresent()){
            return new ResponseEntity<>("Order not founded!", HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(petModelOptional.get(), HttpStatus.FOUND);
        }
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> editPetOrder(@PathVariable(value = "id") Long id, @RequestBody PetModel petModelDto){

        Optional<PetModel> petModelOptional = petRepo.findById(id);

        if(!petModelOptional.isPresent()){
            return new ResponseEntity<>( "Couldn't find this Order!", HttpStatus.NOT_FOUND);
        } else {
            PetModel petModel = petModelOptional.get();

            petModel.setPetName(petModelDto.getPetName());
            petModel.setSpecie(petModelDto.getSpecie());
            petModel.setBreed(petModelDto.getBreed());
            petModel.setHeight(petModelDto.getHeight());
            petModel.setCoatType(petModelDto.getCoatType());
            petModel.setTutorName(petModelDto.getTutorName());
            petModel.setTutorCel(petModelDto.getTutorCel());

            BeanUtils.copyProperties(petModelDto, petModel);
            petModel.setId(petModelOptional.get().getId());

            return new ResponseEntity<>(petServ.savePetOrder(petModel), HttpStatus.OK);
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePetOrder(@PathVariable(value = "id") Long id){
        Optional<PetModel> petModelOptional = petRepo.findById(id);

        if(!petModelOptional.isPresent()){
            return new ResponseEntity<>("Couldn't find this Order!", HttpStatus.NOT_FOUND);
        } else {
            petServ.delete(petModelOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
