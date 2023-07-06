package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ExchangeParticipant;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ExchangeParticipantRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class ExchangeParticipantDeserializer extends JsonDeserializer<ExchangeParticipant> {


    private ExchangeParticipantRepository exchangeParticipantRepository = (ExchangeParticipantRepository) RepositoryService.getRepos().get(ExchangeParticipant.class);

    @Override
    public ExchangeParticipant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int code = p.getValueAsInt();
        return exchangeParticipantRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
