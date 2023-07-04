package com.kozhanov.creditFinanceInstitutionHandbook.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AvailableTransferServiceRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.RegulationAccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RegulationAccountTypeDeserializer extends JsonDeserializer<RegulationAccountType> {
    @Autowired
    private RegulationAccountTypeRepository regulationAccountTypeRepository;

    @Override
    public RegulationAccountType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getValueAsString();
        return regulationAccountTypeRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
