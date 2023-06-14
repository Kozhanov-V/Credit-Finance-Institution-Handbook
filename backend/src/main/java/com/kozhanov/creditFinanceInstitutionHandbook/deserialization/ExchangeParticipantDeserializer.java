package com.kozhanov.creditFinanceInstitutionHandbook.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ExchangeParticipant;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ExchangeParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ExchangeParticipantDeserializer extends JsonDeserializer<ExchangeParticipant> {
    @Autowired
    private ExchangeParticipantRepository exchangeParticipantRepository;

    @Override
    public ExchangeParticipant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int code = p.getValueAsInt();
        return exchangeParticipantRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
