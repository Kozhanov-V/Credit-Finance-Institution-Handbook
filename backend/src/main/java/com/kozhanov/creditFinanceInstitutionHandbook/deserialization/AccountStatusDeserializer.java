package com.kozhanov.creditFinanceInstitutionHandbook.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.RegulationAccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AccountStatusDeserializer extends JsonDeserializer<AccountStatus> {
    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Override
    public AccountStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getValueAsString();
        return accountStatusRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
