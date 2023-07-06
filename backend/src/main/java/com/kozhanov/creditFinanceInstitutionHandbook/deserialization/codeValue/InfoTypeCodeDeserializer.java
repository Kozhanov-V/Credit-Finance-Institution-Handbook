package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.InfoTypeCode;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.InfoTypeCodeRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class InfoTypeCodeDeserializer extends JsonDeserializer<InfoTypeCode> {


    private InfoTypeCodeRepository infoTypeCodeRepository = (InfoTypeCodeRepository) RepositoryService.getRepos().get(InfoTypeCode.class);

    @Override
    public InfoTypeCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getValueAsString();
        InfoTypeCode infoTypeCode= infoTypeCodeRepository.findById(code).get();
        return infoTypeCode;
    }
}

