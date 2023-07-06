package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AvailableTransferServiceRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class AvailableTransferServiceDeserializer extends JsonDeserializer<AvailableTransferService> {


    private AvailableTransferServiceRepository availableTransferServiceRepository = (AvailableTransferServiceRepository) RepositoryService.getRepos().get(AvailableTransferService.class);

    @Override
    public AvailableTransferService deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int code = p.getValueAsInt();
        return availableTransferServiceRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
