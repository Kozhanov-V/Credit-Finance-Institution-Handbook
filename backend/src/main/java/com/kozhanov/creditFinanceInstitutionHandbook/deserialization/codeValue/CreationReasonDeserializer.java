package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.CreationReason;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.CreationReasonRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class CreationReasonDeserializer extends JsonDeserializer<CreationReason> {

    private CreationReasonRepository creationReasonRepository = (CreationReasonRepository) RepositoryService.getRepos().get(CreationReason.class);

    @Override
    public CreationReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getValueAsString();
        CreationReason creationReason = creationReasonRepository.findByCode(code).get();
        return creationReason;

    }
}

