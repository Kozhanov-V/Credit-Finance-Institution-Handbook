package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.RegulationAccountTypeRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class RegulationAccountTypeDeserializer extends JsonDeserializer<RegulationAccountType> {


    private RegulationAccountTypeRepository regulationAccountTypeRepository= (RegulationAccountTypeRepository) RepositoryService.getRepos().get(RegulationAccountType.class);

    @Override
    public RegulationAccountType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getValueAsString();
        return regulationAccountTypeRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
