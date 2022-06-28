package com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.controller;

import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.application.PersonaService;
import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.domain.Persona;
import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.dto.input.PersonaInputDTO;
import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.dto.output.PersonaOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addPersona")
    public String addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        personaService.addPersona(personaInputDTO);
        return "Persona añadida";
    }

    @GetMapping("/getPersonaID/{id}")
    public PersonaOutputDTO getPersonaByID(@PathVariable int id) throws Exception {
        return personaService.getPersonaByID(id);
    }


    @GetMapping("/getPersonaName/{name}")
    public List<PersonaOutputDTO> getPersonaByName(@PathVariable String name){
        return personaService.getPersonaByName(name);
    }

    @GetMapping("/getAll")
    public List <PersonaOutputDTO> getAll(){
        return personaService.getAll();
    }

    @PutMapping("/update/{id}")
    public String updatePersona(@RequestBody PersonaInputDTO personaInputDTO, @PathVariable int id){
        personaService.updatePersona(id, personaInputDTO);
        return "Persona actualziada";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePersona(@PathVariable int id){
        personaService.deletePersona(id);
        return "Persona eliminada";
    }

}