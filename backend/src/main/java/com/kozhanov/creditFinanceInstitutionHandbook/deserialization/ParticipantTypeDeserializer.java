package com.kozhanov.creditFinanceInstitutionHandbook.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ParticipantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

public class ParticipantTypeDeserializer extends JsonDeserializer<ParticipantType> {

    @Autowired
    private ParticipantTypeRepository participantTypeRepository;

    @Override
    public ParticipantType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int code = p.getValueAsInt();
        return participantTypeRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
