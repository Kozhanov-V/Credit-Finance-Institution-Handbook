package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class AccountStatusDeserializer extends JsonDeserializer<AccountStatus> {

    private AccountStatusRepository accountStatusRepository= (AccountStatusRepository) RepositoryService.getRepos().get(AccountStatus.class);

    @Override
    public AccountStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getValueAsString();
        return accountStatusRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ParticipantType code: " + code));
    }
}
