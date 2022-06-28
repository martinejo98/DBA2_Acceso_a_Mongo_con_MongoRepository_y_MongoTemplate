package com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.infraestructure.repository;

import com.example.DBA2_Acceso_a_Mongo_con_MongoRepository_y_MongoTemplate.persona.domain.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, Integer> {
}
