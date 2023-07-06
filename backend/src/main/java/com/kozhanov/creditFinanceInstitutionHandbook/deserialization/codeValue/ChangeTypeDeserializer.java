package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ChangeTypeRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class ChangeTypeDeserializer extends JsonDeserializer<ChangeType> {


    private ChangeTypeRepository changeTypeRepository = (ChangeTypeRepository) RepositoryService.getRepos().get(ChangeType.class);

    @Override
    public ChangeType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String code = jsonParser.getValueAsString();
        ChangeType changeType= changeTypeRepository.findById(code).get();
        return changeType;
    }
}