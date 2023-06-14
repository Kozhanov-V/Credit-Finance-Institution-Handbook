package com.kozhanov.creditFinanceInstitutionHandbook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.ParticipantTypeDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ParticipantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class JacksonConfig {

//    @Autowired
//    private ParticipantTypeRepository participantTypeRepository;
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        //module.addDeserializer(ParticipantType.class, new ParticipantTypeDeserializer(participantTypeRepository));
//        objectMapper.registerModule(module);
//        return objectMapper;
//    }
}
