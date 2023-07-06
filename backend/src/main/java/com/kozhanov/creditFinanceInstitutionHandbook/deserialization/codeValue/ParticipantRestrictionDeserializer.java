package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantRestriction;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ParticipantRestrictionRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class ParticipantRestrictionDeserializer extends JsonDeserializer<ParticipantRestriction> {


    private ParticipantRestrictionRepository participantRestrictionRepository = (ParticipantRestrictionRepository) RepositoryService.getRepos().get(ParticipantRestriction.class);

    @Override
    public ParticipantRestriction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String code = jsonParser.getValueAsString();
        ParticipantRestriction participantRestriction= participantRestrictionRepository.findById(code).get();
        return participantRestriction;
    }
}
