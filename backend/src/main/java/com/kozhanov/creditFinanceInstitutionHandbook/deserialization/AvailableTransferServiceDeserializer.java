package com.kozhanov.creditFinanceInstitutionHandbook.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AvailableTransferServiceRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ParticipantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AvailableTransferServiceDeserializer extends JsonDeserializer<AvailableTransferService> {

    @Autowired
    private AvailableTransferServiceRepository availableTransferServiceRepository;

    @Override
    public AvailableTransferService deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int code = p.getValueAsInt();
        return availableTransferServiceRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
