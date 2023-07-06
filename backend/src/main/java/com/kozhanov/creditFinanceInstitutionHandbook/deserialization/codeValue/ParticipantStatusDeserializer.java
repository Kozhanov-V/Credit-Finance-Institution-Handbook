package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ParticipantStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class ParticipantStatusDeserializer extends JsonDeserializer<ParticipantStatus> {


    private ParticipantStatusRepository participantStatusRepository = (ParticipantStatusRepository) RepositoryService.getRepos().get(ParticipantStatus.class);

    @Override
    public ParticipantStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String code = jsonParser.getValueAsString();
        ParticipantStatus participantStatus= participantStatusRepository.findById(code).get();
        return participantStatus;
    }
}