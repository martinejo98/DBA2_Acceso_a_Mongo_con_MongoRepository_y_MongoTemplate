package com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.application;

import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.domain.Persona;
import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.dto.input.PersonaInputDTO;
import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.dto.output.PersonaOutputDTO;
import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;

    public PersonaOutputDTO addPersona(PersonaInputDTO persona) throws Exception {
        if(persona.getUsuario() == null || persona.getPassword() == null || persona.getName() == null || persona.getCompany_email() == null
                || persona.getPersonal_email() == null || persona.getCity() == null || persona.getActive() == null
                || persona.getUsuario().length() > 10) throw new Exception("Los campos no se han establecido de manera correcta");
        Persona personaEntity = new Persona();
        personaEntity.setId_persona(persona.getId_persona());
        personaEntity.setUsuario(persona.getUsuario());
        personaEntity.setPassword(persona.getPassword());
        personaEntity.setName(persona.getName());
        personaEntity.setSurname(persona.getSurname());
        personaEntity.setCompany_email(persona.getCompany_email());
        personaEntity.setPersonal_email(persona.getPersonal_email());
        personaEntity.setCity(persona.getCity());
        personaEntity.setActive(persona.getActive());
        personaEntity.setCreated_date(persona.getCreated_date());
        personaEntity.setImagen_url(persona.getImagen_url());
        personaEntity.setTermination_date(persona.getTermination_date());
        personaRepository.save(personaEntity);
        return null;
    }

    public PersonaOutputDTO getPersonaByID(int id) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow(()->new Exception("No se ha encontrado a la persona con el id: "+id));
        PersonaOutputDTO personaOutputDTO = modelMapper.map(persona, PersonaOutputDTO.class);
        return personaOutputDTO;
    }

    public List<PersonaOutputDTO> getPersonaByName(String name){

        List <PersonaOutputDTO> listaPersonasDTO = new ArrayList<>();
        personaRepository.findAll().forEach(
                persona -> {
                    if(persona.getName().equals(name)){
                        PersonaOutputDTO personaOnputDTO =modelMapper.map(persona, PersonaOutputDTO.class);
                        listaPersonasDTO.add(personaOnputDTO);
                    }
                }
        );

        return listaPersonasDTO;
    }

    public List<PersonaOutputDTO> getAll(){
        List <PersonaOutputDTO> listaPersonas = new ArrayList<>();
        personaRepository.findAll().forEach(
                persona -> {
                    PersonaOutputDTO personaOutputDTO = modelMapper.map(persona, PersonaOutputDTO.class);
                    listaPersonas.add(personaOutputDTO);
                }
        );
        return listaPersonas;
    }

    public PersonaOutputDTO updatePersona(int id, PersonaInputDTO personaInputDTO) {

        personaRepository.findAll().forEach(
                persona -> {
                    if(persona.getId_persona()==id){
                        persona.setId_persona(personaInputDTO.getId_persona());
                        persona.setUsuario(personaInputDTO.getUsuario());
                        persona.setPassword(personaInputDTO.getPassword());
                        persona.setName(personaInputDTO.getName());
                        persona.setSurname(personaInputDTO.getSurname());
                        persona.setCompany_email(personaInputDTO.getCompany_email());
                        persona.setPersonal_email(personaInputDTO.getPersonal_email());
                        persona.setCity(personaInputDTO.getCity());
                        persona.setActive(personaInputDTO.getActive());
                        persona.setCreated_date(personaInputDTO.getCreated_date());
                        persona.setImagen_url(personaInputDTO.getImagen_url());
                        persona.setTermination_date(personaInputDTO.getTermination_date());
                        personaRepository.save(persona);
                    }
                }
        );

        PersonaOutputDTO personaOutputDTO = modelMapper.map(personaInputDTO, PersonaOutputDTO.class);

        return personaOutputDTO;

    }

    public String deletePersona(int id){
        personaRepository.deleteById(id);
        return "Persona eliminada";
    }
}
